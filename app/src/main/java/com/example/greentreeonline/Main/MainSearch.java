package com.example.greentreeonline.Main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.AdapterProduct;
import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainSearch extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> list;
    AdapterProduct adapter;
    SearchView searchView;
    String urlsearch= ConnectServer.search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreach);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        recyclerView = findViewById(R.id.rcsearch);
        searchView = findViewById(R.id.search);
//        searchView.setQueryHint("Bạn cần mua gì....");



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                list = new ArrayList<>();
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AdapterProduct(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);

                event();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void event() {
        String tukhoa = searchView.getQuery().toString().trim();
        final String query = "SELECT * FROM trangchu WHERE  tensp LIKE '%" + tukhoa + "%'OR giasp LIKE '%"+tukhoa+"%'";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, urlsearch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    if (response.contains("null")) {
                        Toast.makeText(MainSearch.this, "lỗi tìm kiếm", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONArray jsonArray = new JSONArray(response);
                        Log.e( "onResponse: ", response.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonsp = jsonArray.getJSONObject(i);
                                int id = jsonsp.getInt("id");
                                String tensp = jsonsp.getString("tensp");
                                int gia = jsonsp.getInt("giasp");
                                String igmsp = jsonsp.getString("igmsp");
                                String mota = jsonsp.getString("mota");

                                list.add(new Product(id, tensp, gia, igmsp, mota));
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainSearch.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e( "onResponse: ",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tukhoa", query);
                return params;
            }
        };
        requestQueue.add(request);

    }
}