package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Class.Sale;
import com.example.greentreeonline.Main.Sale.MainInfoSale;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterSale extends RecyclerView.Adapter<AdapterSale.ViewHolder>  {
    Context context;
    ArrayList<Sale> listsale;


    public AdapterSale(Context applicationContext, ArrayList<Sale> sp) {
        this.context= applicationContext;
        this.listsale= sp;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.itemsale,null);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Sale sp = listsale.get(position);
        holder.tvTen.setText(sp.getTenspsale());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvgiacu.setText("đ" + decimalFormat.format(sp.getGtsale()));
        holder.tvgia.setText("đ" + decimalFormat.format(sp.sale()));
        holder.tvsale.setText("-" + sp.getSale() + "%");
        Picasso.get().load(sp.getImgspsale()).centerCrop().resize(150, 150).into(holder.imageView);
    }
        @Override
        public int getItemCount() {
            return listsale.size();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTen, tvgiacu, tvgia, tvsale;


        public ViewHolder(View itemView) {
            super(itemView);


            imageView = (ImageView) itemView.findViewById(R.id.img_sanpham);
            tvTen = (TextView) itemView.findViewById(R.id.tvTenSpsale);
            tvgiacu = (TextView) itemView.findViewById(R.id.tvGiacu);
            tvgia = (TextView) itemView.findViewById(R.id.tvGiamoisale);
            tvsale = (TextView) itemView.findViewById(R.id.sale);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // objcay cay = listcay.get(getAdapterPosition());
                    Intent intent = new Intent(context, MainInfoSale.class);
                    intent.putExtra("homesale", listsale.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Toast.makeText(context, listcay.get(getPosition()).getProductName(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }

}
