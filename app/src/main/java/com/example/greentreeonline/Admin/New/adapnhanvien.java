package com.example.greentreeonline.Admin.New;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Admin.sanpham;
import com.example.greentreeonline.Admin.updatesanpham;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Class.New.Staff;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapnhanvien extends RecyclerView.Adapter<adapnhanvien.ViewHolder> {
    nhanvien context;
    ArrayList<Staff> listkh;


    public adapnhanvien(nhanvien context, ArrayList<Staff> sp) {
        this.context = context;
        this.listkh =sp;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_nhanvien,null);
        return new adapnhanvien.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Staff product = listkh.get(position);
        holder.tennv.setText(product.getTennv());
        //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.emailnv.setText((product.getEmailnv() + ""));
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoa(position);
            }
        });

        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updatenhanvien.class);
                intent.putExtra("update", product);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return listkh.size();
    }

    @Override
    public int getItemCount() {
        return listkh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tennv, emailnv;
        public Button xoa, sua;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tennv = (TextView) itemView.findViewById(R.id.tennv);
            emailnv = (TextView) itemView.findViewById(R.id.emailnv);
            xoa = (Button) itemView.findViewById(R.id.xoa);
            sua = (Button) itemView.findViewById(R.id.sua);
        }
    }



    public void xoa(int pos) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Bạn có muốn xoá nhân viên" + listkh.get(pos).getTennv() + " ?");

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.xoasanpham(pos);
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}
