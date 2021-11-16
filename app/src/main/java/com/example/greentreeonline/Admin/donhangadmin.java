package com.example.greentreeonline.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.Bill.AdapterAdminBill;
import com.example.greentreeonline.Adapter.Bill.AdapterBill;
import com.example.greentreeonline.Class.New.Bill;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.BillFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.Bill.MainBill;
import com.example.greentreeonline.Main.Bill.MainInfoBill;
import com.example.greentreeonline.Main.Bill.MainStatusBill;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.greentreeonline.Fragment.FragmentHome.objdh;

public class donhangadmin extends AppCompatActivity {
    //   public static ArrayList<objdonhang> objdh;
    AdapterAdminBill adapdh;
    ListView listView;
    Toolbar toolbar;
    String urldh = ConnectServer.donhang;
    private ArrayList<Bill> objdh;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferences1;
    public static SharedPreferences.Editor editor1;

    BillFirebase billFirebase;

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoadon);
        System.out.println("123");
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        sharedPreferences1 = this.getSharedPreferences("chitiet", this.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();

        billFirebase = new BillFirebase();

        listView = findViewById(R.id.lvdh);
        toolbar = findViewById(R.id.tbdonhang);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        event();
        getdonhang();
    }

    public void huydonhang(final int idsp) {
        Toast.makeText(donhangadmin.this, "  Đơn hàng đã bị hủy", Toast.LENGTH_SHORT).show();
        getdonhang();
        finish();
    }

    public void event() {
        objdh = new ArrayList<>();
        adapdh = new AdapterAdminBill(donhangadmin.this, objdh);
        listView.setAdapter(adapdh);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor1.putString("madonhang", objdh.get(position).getId());
                editor1.putString("tentk", objdh.get(position).getTenkh().toString());
                editor1.putString("ngay", objdh.get(position).getDate().toString());
                editor1.putString("sdt", objdh.get(position).getDt());
                editor1.putString("diachi", objdh.get(position).getDiachi().toString());
                editor1.putInt("phiship", objdh.get(position).getPhiship());
                editor1.putInt("tamtinh", objdh.get(position).getTamtinh());
                editor1.putInt("tongtien", objdh.get(position).getTongtien());
                editor1.putString("trangthai", objdh.get(position).getNamestatus().toString());
                editor1.putString("admin", "admin");
                editor1.commit();
                Intent intent = new Intent(donhangadmin.this, MainBill.class);
                intent.putExtra("madonhang", objdh.get(position).getId() + "");
                intent.putExtra("sdt", "0" + objdh.get(position).getDt() + "");
                intent.putExtra("tenkh", objdh.get(position).getTenkh().toString());
                intent.putExtra("diachi", objdh.get(position).getDiachi().toString());
                intent.putExtra("ngay", objdh.get(position).getDate().toString());
                intent.putExtra("phiship", objdh.get(position).getPhiship() + "đ");
                intent.putExtra("tamtinh", objdh.get(position).getTamtinh() + "đ");
                intent.putExtra("tongtien", objdh.get(position).getTongtien() + "đ");
                intent.putExtra("trangthai", " Đơn hàng " + objdh.get(position).getNamestatus().toString());
                startActivity(intent);
            }
        });
    }

    public void getdonhang() {
        billFirebase.getBillByShopId(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(Bill bill: (ArrayList<Bill>) obj){
                    objdh.add(bill);
                }
                adapdh.notifyDataSetChanged();
            }
        });
    }
}


