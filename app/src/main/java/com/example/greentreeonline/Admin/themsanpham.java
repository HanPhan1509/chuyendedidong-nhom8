package com.example.greentreeonline.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.R;

import java.util.HashMap;
import java.util.Map;

public class themsanpham extends AppCompatActivity {

    EditText id, ten, img, gia, mota;
    Button addkh;

    String url = "http://192.168.43.28/server/addsanpham.php";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsanpham);

        ten = (EditText) findViewById(R.id.addtensp);
        img = (EditText) findViewById(R.id.addlink);
        gia = (EditText) findViewById(R.id.addgia);
        mota = (EditText) findViewById(R.id.addmota);
        addkh = (Button) findViewById(R.id.addsanpham);

        event();
    }

    public void event() {
       toolbar = findViewById(R.id.tooladd);
        toolbar.setNavigationIcon(R.drawable.back2);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(themsanpham.this, sanpham.class);
                startActivity(intent);
            }
        });
        addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten1 = ten.getText().toString().trim();
                String img1 = img.getText().toString().trim();
                String gia1 = gia.getText().toString().trim();
                String mota1 = mota.getText().toString().trim();
                if (ten1.isEmpty() || img1.isEmpty() || gia1.isEmpty() || mota1.isEmpty()) {
                    Toast.makeText(themsanpham.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    getsp();
                }
            }
        });
    }

    public void getsp() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("onResponse: ", response.toString());
                if (response.equals("ok")) {
                    Toast.makeText(themsanpham.this, "thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(themsanpham.this, sanpham.class));
                } else {
                    Toast.makeText(themsanpham.this, "lỗi ", Toast.LENGTH_SHORT).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(themsanpham.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pra = new HashMap<>();
                pra.put("tensp", ten.getText().toString().trim());
                pra.put("giasp", gia.getText().toString().trim());
                pra.put("igmsp", img.getText().toString().trim());
                pra.put("mota", mota.getText().toString().trim());
                return pra;
            }
        };
        requestQueue.add(stringRequest);
    }
}