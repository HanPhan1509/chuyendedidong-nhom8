package com.example.greentreeonline.Main.Oder;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.New.AdapterBill;
import com.example.greentreeonline.Class.New.Bill;
import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.CartShop;
import com.example.greentreeonline.Class.Switch;
import com.example.greentreeonline.Firebase.BillFirebase;
import com.example.greentreeonline.Firebase.CartFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.Customer.MainCustomer;
import com.example.greentreeonline.Main.MainCart;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.NotificationApp;
import com.example.greentreeonline.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.example.greentreeonline.Fragment.FragmentHome.listgh;

public class MainOrder extends AppCompatActivity {
    Button btkhachhang, btthanhtoan;
    TextView donhang, sdt, dc, tendv, date, chon;
    public static TextView gia, phiship, ten, tamtinh;
    private ArrayList<CartShop> cartShops;
    private AdapterBill adapterBill;
    Toolbar toolbartt;
    RecyclerView rcv_item;
    Random random;
    public static List<Switch> vct = new ArrayList<>();
    private NotificationManagerCompat notificationManagerCompat;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private int tamtinha = 0, phishipa = 0, tongtiena = 0;

    ArrayList<Cart> listgh;

    CartFirebase cartFirebase;

    String idUser;

    BillFirebase billFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(date());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoannew);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        toolbartt = findViewById(R.id.toolBarthanhtoan);

        cartFirebase = new CartFirebase();

        billFirebase = new BillFirebase();

        idUser = sharedPreferences.getString("id", "0");

        phishipa = 30000;

        cartShops = new ArrayList<>();

        toolbartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainOrder.this, MainCart.class);
                startActivity(intent);
            }

        });

        anhxa();
        getDataChiTiet();
//        date();
//        tamtinh();
        getCart();
    }


    private void getDataChiTiet() {
        Intent intent = getIntent();
        String ten1 = intent.getStringExtra("hoten");
        ten.setText(ten1);

        String sdt1 = intent.getStringExtra("sdt");
        sdt.setText(sdt1);
        String diachi1 = intent.getStringExtra("diachi");
        dc.setText(diachi1);
    }

    public void giatien() {
        tongtiena = tamtinha + 30000;
        gia.setText(tongtiena + " VND");
    }

    public void tamtinh() {
        int tongTien = 0;

        for (int i = 0; i < listgh.size(); i++) {
            tongTien += listgh.get(i).tongTien();
        }
        tamtinha = tongTien;
        // DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tamtinh.setText(tongTien + "đ");
    }


    public void anhxa() {
        btkhachhang = findViewById(R.id.ttkhachhang);
        donhang = findViewById(R.id.donhang);
        ten = findViewById(R.id.tthoten);
        sdt = findViewById(R.id.ttsdt);
        dc = findViewById(R.id.ttdc);
        gia = findViewById(R.id.sotien);
        rcv_item= findViewById(R.id.rcv_item);

        adapterBill = new AdapterBill(getApplicationContext(), cartShops);
        rcv_item.setHasFixedSize(true);
        rcv_item.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rcv_item.setAdapter(adapterBill);

        btthanhtoan = findViewById(R.id.bthoadon);
        random = new Random();
        donhang.setText(String.valueOf(random.nextInt(100000)));

        btthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doThanhToan();
            }
        });
        btkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainOrder.this, MainCustomer.class);
                startActivity(intent);
            }
        });
//        chon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent1 = new Intent(thanhtoan.this, vanchuyen.class);
////                startActivity(intent1);
//            }
//        });
    }
    public void getCart() {
        cartFirebase.getCartUserId(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for (CartShop cart : (ArrayList<CartShop>) obj) {
                    cartShops.add(cart);
                }
                setTien();
                adapterBill.notifyDataSetChanged();
            }
        });
    }

    public void setTien(){
        int gia = 0;
        for (CartShop cartShop : cartShops) {
            for(Cart cart: cartShop.getCarts()){
                gia += cart.tongTien();
            }
            gia += 30000;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        this.gia.setText(decimalFormat.format(gia) + " VNĐ");
    }

    private void sendOnChannel1()  {
//        String title = this.donhang.getText().toString();
        String message = "";

        Notification notification = new NotificationCompat.Builder(this, NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.greentreee)
                .setContentTitle("title")
                .setContentText("Bạn đã đặt mua đơn hàng!" + donhang)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }

    private void doThanhToan() {
        final String dh = donhang.getText().toString().trim();
        final String ten1 = ten.getText().toString();
        final String sdt1 = sdt.getText().toString();
        final String diachi = dc.getText().toString();
        final String tien = gia.getText().toString().trim();
        if (ten1.isEmpty() || sdt1.isEmpty() || diachi.isEmpty()) {
            Toast.makeText(this, "Bạn chưa nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Xác nhận đặt hàng ");
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bill bill = new Bill("", idUser, "",ten1, sdt1, diachi, date(), tamtinha, phishipa, tongtiena, 0, "Đang chờ xác nhận");
                billFirebase.addBills(cartShops, bill, new FirebaseCallback() {
                    @Override
                    public void onCallBack(Object obj) {
                        if((boolean) obj == true){
                            Toast.makeText(getApplicationContext(), "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();
                            finishAffinity();
                            Intent intent = new Intent(MainOrder.this, Navigation.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), MainOrder.class));
            }
        });
        builder.show();
    }
    public String date() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH) + 1;
        int day = calendar.get(calendar.DAY_OF_MONTH);
        StringBuffer date1 = new StringBuffer().append(year).append("-").append(month).append("-").append(day);
        return date1.toString();
    }

}