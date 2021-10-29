package com.example.greentreeonline.Main.Bill;

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
import com.example.greentreeonline.Adapter.Bill.AdapterBill;
import com.example.greentreeonline.Class.New.Bill;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.BillFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.greentreeonline.Fragment.FragmentHome.objdh;

public class MainBill extends AppCompatActivity {
    //   public static ArrayList<objdonhang> objdh;
    AdapterBill adapdh;
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

    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoadon);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        sharedPreferences1 = this.getSharedPreferences("chitiet", this.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();

        billFirebase = new BillFirebase();

        getDatachitiet();

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

    public void huydonhang(String id, int pos) {
        billFirebase.removeBill(id, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    objdh.remove(pos);
                    adapdh.notifyDataSetChanged();
                }
            }
        });
        Toast.makeText(MainBill.this, "  Đơn hàng đã bị hủy", Toast.LENGTH_SHORT).show();
    }

    public void event() {
        objdh = new ArrayList<>();
        adapdh = new AdapterBill(MainBill.this, objdh);
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
                editor1.commit();
                Intent intent = new Intent(MainBill.this, MainInfoBill.class);
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

    public void getDatachitiet(){
        Intent intent = getIntent();
        status = Integer.parseInt(intent.getStringExtra("status"));
        System.out.println(status);
    }

    public void getdonhang() {
        billFirebase.getBillByUserId(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if(obj != null) {
                    for (Bill bill : (ArrayList<Bill>) obj) {
                        if (status < 0) {
                            objdh.add(bill);
                        } else if (bill.getStatus() == status) {
                            objdh.add(bill);
                        }
                    }
                    adapdh.notifyDataSetChanged();
                }
            }
        });
    }
}


