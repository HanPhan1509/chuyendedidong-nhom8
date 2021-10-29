package com.example.greentreeonline.Adapter.New;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Main.MainCategory;
import com.example.greentreeonline.Main.MainInfoProduct;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    Context context;
    ArrayList<Product> listhome;
    ArrayList<Product> sreachhome;

    String urlImage = "https://firebasestorage.googleapis.com/v0/b/greentreeonline-9eb47.appspot.com/o/";
    String duoiimg = "?alt=media";

    public AdapterProduct(Context context, ArrayList<Product> listhome){
        this.context = context;
        this.listhome = listhome;
    }

    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_product,null);
        return new AdapterProduct.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        Product sp = listhome.get(position);
        holder.tvTen.setText(sp.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText("Giá: " + decimalFormat.format(sp.getGia()) + " VNĐ");
        Picasso.get().load(urlImage + sp.getImgsp() + duoiimg).centerCrop().resize(150, 150).into(holder.imvHinh);
    }

    @Override
    public int getItemCount() {
        return listhome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvHinh;
        public TextView tvTen, tvGia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinh = (ImageView) itemView.findViewById(R.id.imvHinhSp);
            tvTen = (TextView) itemView.findViewById(R.id.tvTenSp);
            tvGia = (TextView) itemView.findViewById(R.id.tvGiaSp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainInfoProduct.class);
                    intent.putExtra("trangchu", listhome.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
