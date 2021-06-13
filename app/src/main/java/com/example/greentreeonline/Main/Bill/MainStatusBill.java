package com.example.greentreeonline.Main.Bill;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.AdapterStatusBill;
import com.example.greentreeonline.Class.StatusBill;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainStatusBill extends AppCompatActivity {

    ArrayList<StatusBill> mangxacnhan;
    private ListView lvtrangthai;
    private AdapterStatusBill xacnhanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhtrangdonhang);
        anhxa();
        //Actiontoolbar();
        getdatadonhang();
    }

//    //menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menusearch).getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                xacnhanAdapter.filter(s.trim());
//                return false;
//            }
//        });
//        return true;
//    }


    private void getdatadonhang() {
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectServer.trangthai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() != 2) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        mangxacnhan.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int idtk = jsonObject.getInt("idtaikhoan");
                            int madh = jsonObject.getInt("madonhang");
                            String hoten = jsonObject.getString("tenkh");
                            String date = jsonObject.getString("ngay");
                            int sdt = jsonObject.getInt("sdt");
                            String diachi = jsonObject.getString("diachi");
                            int phiship = jsonObject.getInt("phiship");
                            int tamtinh = jsonObject.getInt("tamtinh");
                            int sotien = jsonObject.getInt("tongtien");
                            String trangthai = jsonObject.getString("trangthai");
                            mangxacnhan.add(new StatusBill(idtk, madh, hoten, sdt, diachi, date, phiship, tamtinh, sotien,trangthai));

                            xacnhanAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }


    public void xoadh() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("1")) {
                    getdatadonhang();
                } else {
                    Toast.makeText(MainStatusBill.this, "Lỗi", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("madonhang", String.valueOf(xacnhanAdapter.getid));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void anhxa() {
        mangxacnhan = new ArrayList<>();
        xacnhanAdapter = new AdapterStatusBill(this, mangxacnhan);
        lvtrangthai = findViewById(R.id.lvtrangthai);
        lvtrangthai.setAdapter(xacnhanAdapter);
        // toolbardt = findViewById(R.id.toolbartrangthai);
    }
}
