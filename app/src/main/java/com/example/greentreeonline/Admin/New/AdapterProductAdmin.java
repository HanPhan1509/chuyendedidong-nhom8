package com.example.greentreeonline.Admin.New;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.AdapterCatgory;
import com.example.greentreeonline.Admin.sanpham;
import com.example.greentreeonline.Admin.updatesanpham;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Firebase.BillFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
import com.example.greentreeonline.Main.MainInfoProduct;
import com.example.greentreeonline.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProductAdmin extends RecyclerView.Adapter<AdapterProductAdmin.ViewHolder> {
    sanpham context;
    ArrayList<Product> listkh;
    String urlImage = "https://firebasestorage.googleapis.com/v0/b/greentreeonline-9eb47.appspot.com/o/";
    String duoiimg = "?alt=media";
    ProductFirebase productFirebase;



    public AdapterProductAdmin(sanpham context, ArrayList<Product> sp) {
        this.context = context;
        this.listkh =sp;
        productFirebase = new ProductFirebase();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.itemsanpham,null);
        return new AdapterProductAdmin.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = listkh.get(position);
        holder.tvTen.setText(product.getTensp());
        //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText((product.getGia() + ""));

        Picasso.get().load(urlImage + product.getImgsp() + duoiimg).centerCrop().resize(150, 150).into(holder.imvHinh, new Callback() {
            @Override
            public void onSuccess() {
                Picasso.get().invalidate(urlImage + product.getImgsp() + duoiimg);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoa(product, position);
            }
        });

        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updatesanpham.class);
                intent.putExtra("update", product);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        if(product.getStatus() == 1){
            holder.switch_status.setChecked(true);
        }else{
            holder.switch_status.setChecked(false);
        }

        holder.switch_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    productFirebase.setStatusProduct(product.getIdsp(), 1, new FirebaseCallback() {
                        @Override
                        public void onCallBack(Object obj) {
                            if((boolean) obj == true){
                                Toast.makeText(context, "Cập nhật trạng thái thành công", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Cập nhật trạng thái không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    productFirebase.setStatusProduct(product.getIdsp(), 0, new FirebaseCallback() {
                        @Override
                        public void onCallBack(Object obj) {
                            if((boolean) obj == true){
                                Toast.makeText(context, "Cập nhật trạng thái thành công", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Cập nhật trạng thái không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return listkh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvHinh;
        public TextView tvTen, tvGia;
        public Button xoa, sua;
        public Switch switch_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinh = (ImageView) itemView.findViewById(R.id.adHinhsp);
            tvTen = (TextView) itemView.findViewById(R.id.adTenSP);
            tvGia = (TextView) itemView.findViewById(R.id.adGiaSp);
            xoa = (Button) itemView.findViewById(R.id.xoa);
            sua = (Button) itemView.findViewById(R.id.sua);
            switch_status = itemView.findViewById(R.id.switch_status);
        }
    }



    public void xoa(Product product, int pos) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Bạn có muốn xoá " + product.getTensp() + " ?");

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                context.xoasanpham(product, pos);
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


