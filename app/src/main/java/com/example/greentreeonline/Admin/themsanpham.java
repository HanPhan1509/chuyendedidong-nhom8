package com.example.greentreeonline.Admin;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.AdapterCallback;
import com.example.greentreeonline.Adapter.ArrayAdapterCategory;
import com.example.greentreeonline.Adapter.ArrayAdapterCategoryChild;
import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.Class.Category.CategoryChild;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Firebase.CategoryFireBase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
import com.example.greentreeonline.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class themsanpham extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final int MY_REQUEST_CODE = 10;
    EditText id, ten, img, gia, mota, thuonghieu, xuatxu;
    Button addkh, btn_img;
    ImageView img_upload;
    Spinner spinner_cat, spiner_catchild;
    ArrayList<Category> categoryArrayList;
    ArrayList<CategoryChild> categoryChildren;
    ArrayAdapterCategory arrayAdapterCategory;
    ArrayAdapterCategoryChild adapterCategoryChild;
    boolean openimage = true;

    CategoryFireBase categoryFireBase;

    ProductFirebase productFirebase = new ProductFirebase();

    String idcat = "", idcatChild = "", idUser = "", thuonghieusp, xuatxusp;
    int giasp;
    //Product
    String tensp, desc, imgsp, gias;

    Toolbar toolbar;
    FirebaseStorage storage;
    StorageReference storageRef;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    System.out.println("onActivity");
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            img_upload.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    AdapterCallback adapterCallback = new AdapterCallback() {
        @Override
        public void onCallBack(Object obj) {
            System.out.println(obj);
            if(obj != null) {
                spiner_catchild.setVisibility(View.VISIBLE);
                if (!idcat.equals((String) obj)) {
                    idcat = (String) obj;
                    categoryFireBase.getCategoryChild((String) obj, new FirebaseCallback() {
                        @Override
                        public void onCallBack(Object obj) {
                            categoryChildren.clear();
                            ArrayList<CategoryChild> newarr = (ArrayList<CategoryChild>) obj;
                            for (CategoryChild category : newarr) {
                                categoryChildren.add(category);
                                adapterCategoryChild.notifyDataSetChanged();
                            }
                            adapterCategoryChild.notifyDataSetChanged();
                        }
                    });
                }
            }
        }
    };

    AdapterCallback adapterCallbackChild = new AdapterCallback() {
        @Override
        public void onCallBack(Object obj) {
            idcatChild = (String) obj;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsanpham);

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        categoryArrayList = new ArrayList<Category>();
        categoryChildren = new ArrayList<>();

        spinner_cat = (Spinner) findViewById(R.id.spiner_cat);
        spiner_catchild = findViewById(R.id.spiner_catchild);
        spiner_catchild.setVisibility(View.INVISIBLE);

        Resources res = getResources();

        arrayAdapterCategory = new ArrayAdapterCategory(this, R.layout.item_spiner, categoryArrayList, res, adapterCallback);
        spinner_cat.setAdapter(arrayAdapterCategory);

        adapterCategoryChild = new ArrayAdapterCategoryChild(this, R.layout.item_spiner, categoryChildren, res, adapterCallbackChild);
        spiner_catchild.setAdapter(adapterCategoryChild);

        storage = FirebaseStorage.getInstance("gs://greentreeonline-9eb47.appspot.com/");
        storageRef = storage.getReference();

        categoryFireBase = new CategoryFireBase();

        productFirebase = new ProductFirebase();

        ten = (EditText) findViewById(R.id.addtensp);
        gia = (EditText) findViewById(R.id.addgia);
        mota = (EditText) findViewById(R.id.addmota);
        addkh = (Button) findViewById(R.id.addsanpham);
        btn_img = findViewById(R.id.btn_img);
        img_upload = findViewById(R.id.img_upload);

        thuonghieu = findViewById(R.id.thuonghieu);
        xuatxu = findViewById(R.id.xuatxu);

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        event();
        getCategory();
    }

    public void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallary();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallary();
        }else{
            String [] permisstion = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permisstion, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallary();
            }
        }
    }

    private void openGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select image"));
    }

    public void uploadImage(){
        img_upload.setDrawingCacheEnabled(true);
        img_upload.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) img_upload.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        imgsp = randomNameImg();
        StorageReference mountainsRef = storageRef.child(imgsp);
        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadProduct();
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

    public void uploadProduct(){
        tensp = ten.getText().toString().trim();
        desc = mota.getText().toString().trim();
        gias = gia.getText().toString().trim();
        thuonghieusp = thuonghieu.getText().toString().trim();
        xuatxusp = xuatxu.getText().toString().trim();
        giasp = 0;
        try {
            giasp = Integer.parseInt(gias);
        } catch ( Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Gía sản phẩm là là số!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(xuatxusp.isEmpty() || thuonghieusp.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập tên thương hiệu và xuất sứ sản phẩm!", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product("", tensp, giasp, desc, imgsp, idUser, idcat, idcatChild, thuonghieusp, xuatxusp, 1);
        productFirebase.addProduct(product, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    Toast.makeText(getApplicationContext(), "thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                    openimage = false;
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "thêm sản phẩm không thành thành công, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void event() {
        addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    @Override
    protected void onStop() {
        if(!openimage){
            startActivity(new Intent(this, sanpham.class));
        }
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String randomNameImg() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString + ".jpg";
    }

    public void getCategory(){
        categoryFireBase.getAllCategory(new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                ArrayList<Category> newarr = (ArrayList<Category>) obj;
                for (Category category: newarr){
                    categoryArrayList.add(category);
                    arrayAdapterCategory.notifyDataSetChanged();
                }
            }
        });
    }
}