package com.example.greentreeonline.Main.Bill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.Bill.AdapterInfoBill;
import com.example.greentreeonline.Class.Bill.Bill;
import com.example.greentreeonline.Class.Bill.InfoBill;
import com.example.greentreeonline.Class.New.ProductBill;
import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.BillFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.Oder.MainOrder;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.greentreeonline.Fragment.FragmentHome.listgh;

public class MainInfoBill extends AppCompatActivity {
    TextView donhang, sdt, dc, trangthai, date, chon;
    TextView gia, phiship, ten, tamtinh, tongtien;
    ArrayList<ProductBill> objdh;
    Bill sp;
    ListView listView;
    AdapterInfoBill adtt;
    Button chot, huy;
    Toolbar toolbar;
    //    private SharedPreferences sharedPreferences1;
//    private SharedPreferences.Editor editor1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    String dh;

    BillFirebase billFirebase;

    Spinner spn_status;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiethoadon);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        MainBill.sharedPreferences1 = this.getSharedPreferences("chitiet", this.MODE_PRIVATE);
        MainBill.editor1 = MainBill.sharedPreferences1.edit();

        billFirebase = new BillFirebase();

        toolbar = findViewById(R.id.toolBarchitiet);
        toolbar.setNavigationIcon(R.drawable.back2);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        anhxa();
        getDataChiTiet1();
        getcay();
    }

    private void ThanhToan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Xác nhận chốt đơn ");
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainInfoBill.this, "Chốt đơn thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainInfoBill.this, Navigation.class));
                        listgh.clear();
                        Toast.makeText(MainInfoBill.this, "thất bại! ", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), MainOrder.class));
            }
        });
        builder.show();
    }


    public void anhxa() {

        listView = (ListView) findViewById(R.id.lvinfodonhang);
        objdh = new ArrayList<ProductBill>();
        adtt = new AdapterInfoBill(MainInfoBill.this, objdh);
        listView.setAdapter(adtt);

        donhang = (TextView) findViewById(R.id.ctdonhang);
        ten = findViewById(R.id.cthoten);
        date = findViewById(R.id.date);
        sdt = findViewById(R.id.ctsdt);
        dc = findViewById(R.id.ctdc);
        trangthai = findViewById(R.id.tinhtrang);
        gia = findViewById(R.id.ctsotien);
        tamtinh = findViewById(R.id.cttamtinh);
        phiship = findViewById(R.id.ctphisip);
        tongtien = findViewById(R.id.ctsotien);
    }

    private void getcay() {
        billFirebase.getProductBillByBillId(dh, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for (ProductBill productBill : (ArrayList<ProductBill>) obj) {
                    objdh.add(productBill);
                }
                adtt.notifyDataSetChanged();
            }
        });
    }


    private void getDataChiTiet1() {
        Intent intent = getIntent();
        dh = intent.getStringExtra("madonhang");
        donhang.setText(dh);
        String ten1 = intent.getStringExtra("tenkh");
        ten.setText(ten1);
        String sdt1 = intent.getStringExtra("sdt");
        sdt.setText(sdt1);
        String diachi1 = intent.getStringExtra("diachi");
        dc.setText(diachi1);
        String ngay = intent.getStringExtra("ngay");
        date.setText(ngay);
        String tt = intent.getStringExtra("tongtien");
        tongtien.setText(tt);
        String tam = intent.getStringExtra("tamtinh");
        tamtinh.setText(tam);
        String phi = intent.getStringExtra("phiship");
        phiship.setText(phi);
        String trthai = intent.getStringExtra("trangthai");
        trangthai.setText(trthai);
    }

}
