package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class AdapterCatgory extends RecyclerView.Adapter<AdapterCatgory.ViewHolder> {

    Context context;
    ArrayList<Category> categoryArrayList;
    private AdapterCallback adapterCallback;
    public AdapterCatgory(Context applicationContext, ArrayList<Category> categoryArrayList, AdapterCallback adapterCallback) {
        this.context= applicationContext;
        this.categoryArrayList = categoryArrayList;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_cat,null);
        return new AdapterCatgory.ViewHolder(itemView, adapterCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryArrayList.get(position);
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(@NonNull View itemView, AdapterCallback adapterCallback1) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            adapterCallback = adapterCallback1;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterCallback.onCallBack(categoryArrayList.get(getPosition()).getId());
                }
            });
        }

    }
}
