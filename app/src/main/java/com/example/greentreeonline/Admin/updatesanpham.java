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
import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import org.simple.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class updatesanpham extends AppCompatActivity {
    EditText ten, gia, img, mota;
    Button update;
    int idsp=0;
    Product sp;
    Toolbar toolbar;
    private String urlupdate = ConnectServer.update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatesanpham);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        sp = (Product) intent.getSerializableExtra("updatesanpham");
        Toast.makeText(updatesanpham.this, sp.getTensp(), Toast.LENGTH_SHORT);
        anhxa();

    }

    public void anhxa() {
        ten = findViewById(R.id.uptensp);
        gia=findViewById(R.id.upgia);
        img=findViewById(R.id.uplink);
        mota=findViewById(R.id.upmota);
        ///////////////////////////////
        ten.setText(sp.getTensp());
        gia.setText(sp.getGt()+" VND");
        img.setText(sp.getImgsp());
        mota.setText(sp.getMota());
        toolbar=findViewById(R.id.tool);
        toolbar.setNavigationIcon(R.drawable.back2);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatesanpham.this, sanpham.class);
                startActivity(intent);
            }

        });
        update=findViewById(R.id.btnupdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = ten.getText().toString().trim();
                String gia1 = gia.getText().toString().trim();
                String link = img.getText().toString().trim();
                String mt = mota.getText().toString().trim();
                if (tensp.matches("") || gia1.equals("") || link.equals("") || mt.length()==0) {
                    Toast.makeText(updatesanpham.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    updatesanpham(urlupdate);
                }
            }
        });
    }

    public void updatesanpham(String url) {
        RequestQueue connnect = Volley.newRequestQueue(this);
        StringRequest jsonArray = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e( "onResponse: ",response.toString() );
                if (response.equals("ok")) {
                    Toast.makeText(updatesanpham.this, "Da update !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(updatesanpham.this, Navigation.class));
                }else {
                    Toast.makeText(updatesanpham.this, "Loi!", Toast.LENGTH_SHORT).show();

                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(updatesanpham.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pra = new HashMap<>();
                pra.put("id", String.valueOf(idsp));
                pra.put("tensp", ten.getText().toString().trim());
                pra.put("giasp", gia.getText().toString().trim());
                pra.put("igmsp", img.getText().toString().trim());
                pra.put("mota",mota.getText().toString().trim());
                EventBus.getDefault().post(true, "update");
                return pra;
            }
        };
        connnect.add(jsonArray);


    }

}
