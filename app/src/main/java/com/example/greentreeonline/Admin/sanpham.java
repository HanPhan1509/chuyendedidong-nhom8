package com.example.greentreeonline.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Admin.New.AdapterProductAdmin;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import java.io.File;
import java.util.ArrayList;

public class sanpham extends AppCompatActivity {
    ArrayList<Product> sp;
    RecyclerView recyclerView, resale;
    AdapterProductAdmin adapcay;
    Toolbar tbsanpham;
    ImageButton add;
    ProductFirebase productFirebase;
    String idUser;
    private SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        tbsanpham = findViewById(R.id.tbsanpham);
        productFirebase = new ProductFirebase();

        tbsanpham.setNavigationIcon(R.drawable.back2);
        tbsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sanpham.this, Navigation.class);
                startActivity(intent);
            }

        });
        recyclerView = findViewById(R.id.listview);
        add = findViewById(R.id.themsp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sanpham.this, themsanpham.class);
                startActivity(intent);
            }
        });
        sp = new ArrayList<>();
        adapcay = new AdapterProductAdmin(sanpham.this, sp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapcay);
        deleteCache(getApplicationContext());
        getcay();
    }

    public void xoasanpham(Product product, int pos) {
        productFirebase.removeProduct(product, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    sp.remove(pos);
                    adapcay.notifyDataSetChanged();
                }
            }
        });
    }

    public void getcay() {
        productFirebase.getAllProductUserID(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                ArrayList<Product> arrayList = (ArrayList<Product>) obj;
                for (Product product: arrayList){
                    sp.add(product);
                    adapcay.notifyDataSetChanged();
                }
            }
        });
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}

