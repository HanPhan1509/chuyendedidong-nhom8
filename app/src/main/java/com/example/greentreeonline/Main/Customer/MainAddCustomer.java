package com.example.greentreeonline.Main.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.greentreeonline.Admin.sanpham;
import com.example.greentreeonline.Class.New.Customer;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.CustomerFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainAddCustomer extends AppCompatActivity {
    EditText hoten, sdt, diachi;
    Button addkh;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    CustomerFirebase customerFirebase;

    String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        sharedPreferences = MainAddCustomer.this.getSharedPreferences("luutaikhoan", MainAddCustomer.this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        customerFirebase = new CustomerFirebase();

        hoten = (EditText) findViewById(R.id.hoten);
        sdt = (EditText) findViewById(R.id.sdt);
        diachi = (EditText) findViewById(R.id.diachi);
        addkh = (Button) findViewById(R.id.themkhachhang);
        event();
    }

    public void event() {
        Toolbar bar = findViewById(R.id.tbaddkh);
        bar.setTitle("Hủy bỏ");
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAddCustomer.this, MainCustomer.class);
                startActivity(intent);
            }
        });
        addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = hoten.getText().toString().trim();
                String sodt = sdt.getText().toString().trim();
                String dc = diachi.getText().toString().trim();
                if (ten.isEmpty() || sodt.isEmpty() || dc.isEmpty()) {
                    Toast.makeText(MainAddCustomer.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    Customer customer = new Customer("", idUser, ten, sodt, dc);
                    addCustomer(customer);
                }
            }
        });
    }
    public void addCustomer(Customer customer){
        customerFirebase.addCustomer(customer, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    finish();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        startActivity(new Intent(this, MainCustomer.class));
        super.onStop();
    }
}
