package com.example.greentreeonline.Main.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.greentreeonline.Adapter.AdapterCustomer;
import com.example.greentreeonline.Class.New.Customer;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.CustomerFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.Oder.MainOrder;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class MainCustomer extends AppCompatActivity {
    Button addkh;
    ListView lvkh;
    Toolbar toolbar;
    AdapterCustomer adapkhachhang;
    ArrayList<Customer> kh;
    String urlkh = ConnectServer.khachhang;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    CustomerFirebase customerFirebase;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);
        sharedPreferences = MainCustomer.this.getSharedPreferences("luutaikhoan", MainCustomer.this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        customerFirebase = new CustomerFirebase();

        anhxa();
        event();
        getkhachhang();
    }

    public void anhxa() {
        addkh = (Button) findViewById(R.id.addkhachhang);
        lvkh = (ListView) findViewById(R.id.lvdiachi);
        kh = new ArrayList<Customer>();
        adapkhachhang = new AdapterCustomer(MainCustomer.this, kh);
        lvkh.setAdapter(adapkhachhang);
        toolbar = (Toolbar) findViewById(R.id.tbkhachhang);
    }

    public void event() {
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCustomer.this, MainOrder.class);
                startActivity(intent);
            }

        });
        addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCustomer.this, MainAddCustomer.class);
                startActivity(intent);
            }
        });
        lvkh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(MainCustomer.this, MainOrder.class);
                intent.putExtra("hoten", kh.get(i).getHoten().toString());
                intent.putExtra("sdt",  kh.get(i).getSdt());
                intent.putExtra("diachi", kh.get(i).getDiachi().toString());
                startActivity(intent);
            }
        });
    }
    private void getkhachhang() {
        customerFirebase.getCustomerUserId(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(Customer customer : (ArrayList<Customer>) obj){
                    kh.add(customer);
                }
                adapkhachhang.notifyDataSetChanged();
            }
        });
    }
}