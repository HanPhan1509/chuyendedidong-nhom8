package com.example.greentreeonline.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.New.AdapterProduct;
import com.example.greentreeonline.Class.Category.CategoryChild;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Class.New.SortProduct;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainCategory extends AppCompatActivity {
    CategoryChild categoryChild;
    Toolbar name;
    RecyclerView rcv_product;
    AdapterProduct adapterProduct;
    ArrayList<Product> productArrayList;
    ProductFirebase productFirebase;
    ImageView sort;
    SortProduct sortProduct;
    boolean sorted = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailcat);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        name = findViewById(R.id.textView);
        setSupportActionBar(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sort = findViewById(R.id.sort);


        rcv_product = findViewById(R.id.rcv_product);
        productArrayList = new ArrayList<>();
        adapterProduct = new AdapterProduct(this, productArrayList);
        rcv_product.setHasFixedSize(true);
        rcv_product.setLayoutManager(new GridLayoutManager(this, 2));
        rcv_product.setAdapter(adapterProduct);

        productFirebase = new ProductFirebase();

        Intent intent = getIntent();
        categoryChild = (CategoryChild) intent.getSerializableExtra("danhsach");
        name.setTitle(categoryChild.getName());

        getProduct();

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sorted) {
                    sortProduct = new SortProduct(productArrayList);
                    ArrayList<Product> products = sortProduct.getSortedProductByPrice();
                    productArrayList = products;
                    adapterProduct.notifyDataSetChanged();
                    sorted = true;
                }else{
                    sortProduct = new SortProduct(productArrayList);
                    ArrayList<Product> products = sortProduct.getSortedProductByPriceDes();
                    productArrayList = products;
                    adapterProduct.notifyDataSetChanged();
                    sorted = false;
                }
            }
        });
    }

    public void getProduct(){
        productFirebase.getProductByIdCatChild(categoryChild.getId(), new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(Product product: (ArrayList<Product>) obj){
                    productArrayList.add(product);
                }
                adapterProduct.notifyDataSetChanged();
            }
        });
    }
}
