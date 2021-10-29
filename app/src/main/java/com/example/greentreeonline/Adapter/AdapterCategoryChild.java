package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.Class.Category.CategoryChild;
import com.example.greentreeonline.Main.MainCategory;
import com.example.greentreeonline.Main.MainInfoProduct;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class AdapterCategoryChild extends RecyclerView.Adapter<AdapterCategoryChild.ViewHolder> {
    Context context;
    ArrayList<CategoryChild> categoryChildren;
    public AdapterCategoryChild(Context applicationContext, ArrayList<CategoryChild> categoryChildren) {
        this.context= applicationContext;
        this.categoryChildren = categoryChildren;
    }

    @NonNull
    @Override
    public AdapterCategoryChild.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_cat,null);
        return new AdapterCategoryChild.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryChild.ViewHolder holder, int position) {
        CategoryChild categoryChild = categoryChildren.get(position);
        holder.name.setText(categoryChild.getName());
    }

    @Override
    public int getItemCount() {
        return categoryChildren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MainCategory.class);
                    intent.putExtra("danhsach", categoryChildren.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Toast.makeText(context, listcay.get(getPosition()).getProductName(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
