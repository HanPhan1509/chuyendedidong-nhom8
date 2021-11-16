package com.example.greentreeonline.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.AdapterCallback;
import com.example.greentreeonline.Adapter.AdapterCategoryChild;
import com.example.greentreeonline.Adapter.AdapterCatgory;
import com.example.greentreeonline.Adapter.AdapterProduct;
import com.example.greentreeonline.Adapter.AdapterSale;
import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.Class.Category.CategoryChild;
import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.Class.Sale;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.CategoryFireBase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.MainCart;
import com.example.greentreeonline.Main.MainSearch;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FractmentCategory extends Fragment {
    AdapterCatgory adapterCatgory;
    AdapterCategoryChild adapterCategoryChild;
    ArrayList<Category> categoryArrayList;
    ArrayList<CategoryChild> categoryChildren;
    RecyclerView cat, catchild;
    CategoryFireBase categoryFireBase;
    public FractmentCategory(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category, container, false);
        cat = view.findViewById(R.id.rcv_cat);
        catchild = view.findViewById(R.id.rcv_catchild);
        categoryFireBase = new CategoryFireBase();
        categoryArrayList = new ArrayList<>();
        categoryChildren = new ArrayList<>();

        //set adapterCat
        adapterCatgory = new AdapterCatgory(getActivity(), categoryArrayList, adapterCallback);
        cat.setHasFixedSize(true);
        cat.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        cat.setAdapter(adapterCatgory);
        //set adapcatchild
        adapterCategoryChild = new AdapterCategoryChild(getActivity(), categoryChildren);
        catchild.setHasFixedSize(true);
        catchild.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        catchild.setAdapter(adapterCategoryChild);

//        gh= view.findViewById(R.id.daogiohang);
//
//        //adap = new adapsale(getActivity(), obj);
//        recyclerView = view.findViewById(R.id.test);
//        tvsreach=view.findViewById(R.id.tvsreach);
//        recyclerView1= view.findViewById(R.id.dodung);
//        ///////////////////////////////
//        obj = new ArrayList<Sale>();
//        adap = new AdapterSale(getActivity(), obj);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        recyclerView.setAdapter(adap);
//        //////////////////////////////////
//
//        objdd= new ArrayList<Product>();
//        adapdd= new AdapterProduct(getActivity(),objdd);
//        recyclerView1.setHasFixedSize(true);
//        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        recyclerView1.setAdapter(adapdd);
        getCategory();
        return view;
    }

    AdapterCallback adapterCallback = new AdapterCallback() {
        @Override
        public void onCallBack(Object obj) {
            categoryFireBase.getCategoryChild((String) obj, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    categoryChildren.clear();
                    ArrayList<CategoryChild> newarr = (ArrayList<CategoryChild>) obj;
                    for (CategoryChild category: newarr){
                        categoryChildren.add(category);
                        adapterCategoryChild.notifyDataSetChanged();
                    }
                }
            });
        }
    };

    public void getCategory(){
        categoryFireBase.getAllCategory(new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                ArrayList<Category> newarr = (ArrayList<Category>) obj;
                for (Category category: newarr){
                    categoryArrayList.add(category);
                    adapterCatgory.notifyDataSetChanged();
                }
            }
        });
    }


}
