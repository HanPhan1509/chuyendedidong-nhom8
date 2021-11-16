package com.example.greentreeonline.Main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
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
import com.example.greentreeonline.Adapter.New.AdapterProduct;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Class.New.SortProduct;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
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
    ProductFirebase productFirebase;
    SortProduct sortProduct;
    ImageView sort;
    boolean sorted = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreach);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        productFirebase = new ProductFirebase();
        recyclerView = findViewById(R.id.rcsearch);
        searchView = findViewById(R.id.search);

        sort = findViewById(R.id.sort);

        searchView.onActionViewExpanded();
//        searchView.setQueryHint("Bạn cần mua gì....");
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterProduct(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                event(query);
//                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sorted) {
                    sortProduct = new SortProduct(list);
                    ArrayList<Product> products = sortProduct.getSortedProductByPrice();
                    list = products;
                    adapter.notifyDataSetChanged();
                    sorted = true;
                }else{
                    sortProduct = new SortProduct(list);
                    ArrayList<Product> products = sortProduct.getSortedProductByPriceDes();
                    list = products;
                    adapter.notifyDataSetChanged();
                    sorted = false;
                }
            }
        });
    }

    private void event(String queryText) {
        productFirebase.searchProduct(queryText, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(Product product: (ArrayList<Product>) obj){
                    list.add(product);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}