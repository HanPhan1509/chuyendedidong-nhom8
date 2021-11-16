package com.example.greentreeonline.Main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.Favourite;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Firebase.CartFirebase;
import com.example.greentreeonline.Firebase.FavouriteFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.R;
import com.example.greentreeonline.Class.Sale;
import com.example.greentreeonline.Main.Login.MainLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;


public class MainInfoProduct extends AppCompatActivity {
    Product sp;
    Sale sl;
    ImageView imgcayct, yt, likered;
    TextView ctcay, ctgia, ctmota, thuonghieu, xuatxu;
    Toolbar toolbar;
    Integer[] soluong;
    EditText edgiatri;
    Button tang, giam, mg;
    FloatingActionButton call;
    ImageButton addgh;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    String urlImage = "https://firebasestorage.googleapis.com/v0/b/greentreeonline-9eb47.appspot.com/o/";
    String duoiimg = "?alt=media";

    String idUser;

    FavouriteFirebase favouriteFirebase;
    CartFirebase cartFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        anhxa();
        getDataChiTiet();
        themgh();
        soluong();
        // muangay();
        // sale();
        onclick();
//      getdatacay();
    }

    private void anhxa() {
        imgcayct = (ImageView) findViewById(R.id.imageView);
        ctcay = (TextView) findViewById(R.id.tensp);
        ctgia = (TextView) findViewById(R.id.giathanh);
        ctmota = (TextView) findViewById(R.id.dacdiem);
        tang = findViewById(R.id.butontang);
        giam = findViewById(R.id.butongiam);
        edgiatri = findViewById(R.id.edtgiatri);
        call = findViewById(R.id.call);
        mg = findViewById(R.id.muangay);
        addgh = (ImageButton) findViewById(R.id.addgiohang);
        likered = findViewById(R.id.likered);
        thuonghieu = findViewById(R.id.thuonghieu);
        xuatxu = findViewById(R.id.xuatxu);

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");


        favouriteFirebase = new FavouriteFirebase();

        cartFirebase = new CartFirebase();

        yt = findViewById(R.id.like);
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(idUser)) {
                    yeuthich();
                } else {
                    Toast.makeText(MainInfoProduct.this, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainLogin.class));
                    finish();
                }
            }
        });
        likered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bothich();
            }
        });

        mg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainInfoProduct.this, MainRate.class);
                intent.putExtra("idsp", sp.getIdsp());
                startActivity(intent);
            }
        });

    }


    public void onclick() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainInfoProduct.this);
                builder.setTitle("Bạn có muốn gọi cho chủ shop ?");

                builder.setNegativeButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:0979951954"));
                        if (ActivityCompat.checkSelfPermission(MainInfoProduct.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            reques();
                            // TODO: Consider calling
                            //    Activity#requestPermissions
                        } else {
                            startActivity(intent);
                        }
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                //   return false;

            }

            private void reques() {
                ActivityCompat.requestPermissions(MainInfoProduct.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }

        });

    }

    public void yeuthich() {
        if(likered.getVisibility() == View.INVISIBLE) {
            System.out.println("yêu thích");
            yt.setVisibility(View.INVISIBLE);
            likered.setVisibility(View.VISIBLE);
            Favourite favourite = new Favourite(idUser, sp.getIdsp(), sp.getTensp(), sp.getGia(), sp.getImgsp());
            favouriteFirebase.addFavourite(favourite, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if ((boolean) obj == true) {
                        System.out.println("thêm vào sản phẩm yêu thích thành công");
                    }
                }
            });
        }
    }

    public void bothich(){
        if(yt.getVisibility() == View.INVISIBLE) {
            System.out.println("bo thích");
            yt.setVisibility(View.VISIBLE);
            likered.setVisibility(View.INVISIBLE);
            favouriteFirebase.deleteFavourite(idUser, sp.getIdsp(), new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if ((boolean) obj == true) {
                        System.out.println("bỏ thích sản phẩm thành công");
                    }
                }
            });
        }
    }


    public void getDataChiTiet() {
        Intent intent = getIntent();
        sp = (Product) intent.getSerializableExtra("trangchu");
        Picasso.get().load(urlImage + sp.getImgsp() + duoiimg).into(imgcayct);
        ctcay.setText(sp.getTensp());
        DecimalFormat decimalformat = new DecimalFormat("###,###,###");
        ctgia.setText(decimalformat.format(sp.getGia()) + " VNĐ");
        ctmota.setText(sp.getMota());
        thuonghieu.setText(sp.getThuonghieu());
        xuatxu.setText(sp.getXuatxu());
    }

    public void soluong() {
        tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(edgiatri.getText().toString()) + 1;

                edgiatri.setText(slmoi + "");


                if (slmoi > 100) {
                    tang.setVisibility(View.INVISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                } else {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                }
            }
        });
        giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(edgiatri.getText().toString()) - 1;

                edgiatri.setText(slmoi + "");
                if (slmoi < 1) {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.INVISIBLE);
                    edgiatri.setText(slmoi + "");
                } else {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                }
            }
        });

    }

    private void themgh() {
        addgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                Cart cart = new Cart(idUser, sp.getIdsp(), sp.getTensp(), sp.getImgsp(), sp.getGia(),soLuong, sp.getIduser());
                cartFirebase.addCart(cart, new FirebaseCallback() {
                    @Override
                    public void onCallBack(Object obj) {
                        if((boolean) obj == true){
                            startActivity(new Intent(MainInfoProduct.this, MainCart.class));
                        }
                    }
                });
            }
        });
    }


}
