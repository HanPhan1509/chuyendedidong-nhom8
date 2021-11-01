package com.example.greentreeonline.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.AdapterCallback;
import com.example.greentreeonline.Adapter.New.AdapterCartShop;
import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.CartShop;
import com.example.greentreeonline.Firebase.CartFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.Login.MainLogin;
import com.example.greentreeonline.Main.Oder.MainOrder;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.greentreeonline.Fragment.FragmentHome.listgh;

public class MainCart extends AppCompatActivity {

    public static TextView tvTongTien;
    Button btthanhtoan, ttmuahag;
    RecyclerView lvGioHang;
    TextView sosanpham;
    ArrayList<Cart> listgh;
    ArrayList<CartShop> cartShops;

    CartFirebase cartFirebase;

//    AdapterCart adapterGioHang;
    AdapterCartShop adapterCartShop;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    String urlImage = "https://firebasestorage.googleapis.com/v0/b/greentreeonline-9eb47.appspot.com/o/";
    String duoiimg = "?alt=media";

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        cartFirebase = new CartFirebase();

        anhxa();
        onclick();
        tongtien();
        getCart();
    }

    public void anhxa() {
        lvGioHang = (RecyclerView) findViewById(R.id.lvGioHang);
        tvTongTien = (TextView) findViewById(R.id.tongtien);
        ttmuahag = (Button) findViewById(R.id.ttmuahang);
        sosanpham = findViewById(R.id.numbercart);

        listgh = new ArrayList<>();

        btthanhtoan = (Button) findViewById(R.id.thanhtoan);
        cartShops = new ArrayList<>();

        lvGioHang.setHasFixedSize(true);
        lvGioHang.setLayoutManager(new GridLayoutManager(this, 1));
        adapterCartShop = new AdapterCartShop(getApplicationContext(), cartShops, adapterCallback);
        lvGioHang.setAdapter(adapterCartShop);
    }

    AdapterCallback adapterCallback = new AdapterCallback() {
        @Override
        public void onCallBack(Object obj) {
            if (obj instanceof ArrayList<?>) {
                ArrayList<Integer> integers = (ArrayList<Integer>) obj;
                xoa(integers.get(0), integers.get(1));
            }else{
                Cart event = (Cart) obj;
                if (event != null) {
                    updateCart(event);
                }
            }
        }
    };

    public void onclick() {
        ttmuahag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCart.this, Navigation.class);
                startActivity(intent);
            }
        });
        btthanhtoan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (cartShops.size() > 0) {

                    String TenTk = sharedPreferences.getString("taikhoan", "");
                    if (!TextUtils.isEmpty(TenTk)) {
                        startActivity(new Intent(getApplicationContext(), MainOrder.class));
                    } else {
                        Toast.makeText(MainCart.this, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainLogin.class));
                        finish();
                    }
                }

            }
        });

    }

    public void updateCart(Cart cart){
        cartFirebase.addCart(cart, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    setTvTongTien();
                }
            }
        });
    }

    public void getCart() {
        cartFirebase.getCartUserId(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for (CartShop cart : (ArrayList<CartShop>) obj) {
                    cartShops.add(cart);
                }
                setTvTongTien();
                adapterCartShop.notifyDataSetChanged();
            }
        });
    }


    public void xoa(int position, int position2) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainCart.this);
        builder.setTitle("Bạn có muốn xoá sản phẩm này?");

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartFirebase.deleteCart(idUser, cartShops.get(position).getIdshop(), cartShops.get(position).getCarts().get(position2).getIdsp(), new FirebaseCallback() {
                    @Override
                    public void onCallBack(Object obj) {
                        if ((boolean) obj) {
                            cartShops.get(position).getCarts().remove(position);
                            adapterCartShop.notifyDataSetChanged();
                            setTvTongTien();
                        }
                    }
                });
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void setTvTongTien() {
        int gia = 0;
        int sosp = 0;
        for (CartShop cartShop : cartShops) {
            for(Cart cart: cartShop.getCarts()){
                gia += cart.tongTien();
                sosp++;
            }
        }
        sosanpham.setText(sosp + "");

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTien.setText(decimalFormat.format(gia) + " VNĐ");
    }

    public static void tongtien() {

//        int tongTien = 0;
//        for (int i = 0; i < this.listgh.size(); i++) {
//            tongTien += listgh.get(i).tongTien();
//        }
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        tvTongTien.setText(decimalFormat.format(tongTien) + " VNĐ");
        //}
    }
}

