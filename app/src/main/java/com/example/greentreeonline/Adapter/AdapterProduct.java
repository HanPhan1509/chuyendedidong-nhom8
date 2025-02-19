package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.Main.MainInfoProduct;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>  {
    Context context;
    ArrayList<Product> listhome;
    ArrayList<Product> sreachhome;

    public AdapterProduct(Context applicationContext, ArrayList<Product> sp) {
        this.context= applicationContext;
        this.listhome= sp;
//        this.sreachhome= new ArrayList<objhome>();
//        this.sreachhome.addAll(listhome);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_product,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Product sp = listhome.get(position);
        holder.tvTen.setText(sp.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText("Giá: " + decimalFormat.format(sp.getGia()) + " VNĐ");
         Picasso.get().load(sp.getImgsp()).centerCrop().resize(150, 150).into(holder.imvHinh);
        Log.d(sp.getImgsp(), "  aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: ");

    }


    @Override
    public int getItemCount() {
        return listhome.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imvHinh;
        public TextView tvTen, tvGia;

        public ViewHolder(View itemView) {
            super(itemView);
            imvHinh = (ImageView) itemView.findViewById(R.id.imvHinhSp);
            tvTen = (TextView) itemView.findViewById(R.id.tvTenSp);
            tvGia = (TextView) itemView.findViewById(R.id.tvGiaSp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // objcay cay = listcay.get(getAdapterPosition());
                    Intent intent = new Intent(context, MainInfoProduct.class);
                    intent.putExtra("trangchu", listhome.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Toast.makeText(context, listcay.get(getPosition()).getProductName(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
    public void  filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        listhome.clear();
        if(charText.length() == 0){
            listhome.addAll(sreachhome);
        }else {
            for (Product sp : sreachhome) {
                if (sp.getTensp().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listhome.add(sp);
                }
                if (sp.getMota().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listhome.add(sp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
