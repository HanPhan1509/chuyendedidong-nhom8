package com.example.greentreeonline.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapthongke  extends RecyclerView.Adapter<adapthongke.ItemHolder> {
    Context context;
    ArrayList<objthongke> arrtk;


    public adapthongke(Context context, ArrayList<objthongke> arrtkn) {
        this.context = context;
        this.arrtk = arrtkn;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemthongkethang, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        objthongke thongKeNam = arrtk.get(position);
        holder.txtnam.setText("Năm: " + thongKeNam.getThoigian());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txttien.setText("Tổng doanh thu của năm: "+ decimalFormat.format(thongKeNam.getTongtien()) + "VNĐ");


    }

    @Override
    public int getItemCount() {
        return arrtk.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView txtnam, txttien;


        public ItemHolder(View itemView) {
            super(itemView);
            txtnam=(TextView)itemView.findViewById(R.id.txtnam);
            txttien=(TextView)itemView.findViewById(R.id.txttien);


        }
    }
}
