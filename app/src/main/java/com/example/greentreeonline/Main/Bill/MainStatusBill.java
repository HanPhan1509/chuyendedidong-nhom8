package com.example.greentreeonline.Main.Bill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.greentreeonline.Adapter.AdapterCallback;
import com.example.greentreeonline.Adapter.ArrayAdapterCategory;
import com.example.greentreeonline.Adapter.Bill.AdapterInfoBill;
import com.example.greentreeonline.Class.Bill.Bill;
import com.example.greentreeonline.Class.Bill.InfoBill;
import com.example.greentreeonline.Class.Category.Category;
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

public class MainStatusBill extends AppCompatActivity {
    TextView donhang, sdt, dc, trangthai, date, chon;
    TextView gia, phiship, ten, tamtinh, tongtien;
    ArrayList<ProductBill> objdh;
    Bill sp;
    ListView listView;
    AdapterInfoBill adtt;
    Button chot, huy;
    Toolbar toolbar;
    Spinner spn_status;
    int status;
    //    private SharedPreferences sharedPreferences1;
//    private SharedPreferences.Editor editor1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    ArrayAdapterCategory arrayAdapterCategory;
    ArrayList<Category> categoryArrayList;
    String dh;

    BillFirebase billFirebase;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("staus bill");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbill);
//        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
//        editor = sharedPreferences.edit();

//        MainBill.sharedPreferences1 = this.getSharedPreferences("chitiet", this.MODE_PRIVATE);
//        MainBill.editor1 = MainBill.sharedPreferences1.edit();

        billFirebase = new BillFirebase();

        categoryArrayList = new ArrayList<>();
        Category category = new Category("1", "123");
        categoryArrayList.add(category);

        anhxa();
        getDataChiTiet1();

        Resources res = getResources();

        spn_status = (Spinner) findViewById(R.id.spn_status);
        final List<String> list=new ArrayList<>();

        list.add("Đang chờ xác nhận");
        list.add("Chờ lấy Hàng");
        list.add("Đang giao");
        list.add("Đã giao");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainStatusBill.this,android.R.layout.simple_list_item_1, list);
        spn_status.setAdapter(arrayAdapter);
        spn_status.setSelection(status);

        spn_status.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != status) {
                    status = i;
                    billFirebase.updateBillStatus(dh, i, list.get(i), new FirebaseCallback() {
                        @Override
                        public void onCallBack(Object obj) {
                            if((boolean) obj == true){
                                Toast.makeText(MainStatusBill.this, "Cập nhập trạn thái đơn hàng thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });



        toolbar = findViewById(R.id.toolBarchitiet);
        toolbar.setNavigationIcon(R.drawable.back2);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        getcay();
    }

    private void ThanhToan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Xác nhận chốt đơn ");
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainStatusBill.this, "Chốt đơn thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainStatusBill.this, Navigation.class));
                        listgh.clear();
                        Toast.makeText(MainStatusBill.this, "thất bại! ", Toast.LENGTH_SHORT).show();
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
        adtt = new AdapterInfoBill(MainStatusBill.this, objdh);
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
        status = Integer.parseInt(intent.getStringExtra("status"));
        System.out.println(status);
    }

}
