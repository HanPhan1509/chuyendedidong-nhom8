package com.example.greentreeonline.Adapter.New;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.AdapterCallback;
import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Main.MainCart;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {
    Context context;
    ArrayList<Cart> carts;
    AdapterCallback adapterCallback;

    String urlImage = "https://firebasestorage.googleapis.com/v0/b/greentreeonline-9eb47.appspot.com/o/";
    String duoiimg = "?alt=media";

    public AdapterCart(Context context, ArrayList<Cart> carts, AdapterCallback adapterCallback) {
        this.context = context;
        this.carts = carts;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println(carts.size());
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.itemgiohang,null);
        return new AdapterCart.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart gioHang = carts.get(position);
        Picasso.get().load(urlImage + gioHang.getImgsp() + duoiimg).into(holder.imvHinh);
        holder.tvTen.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText(decimalFormat.format(gioHang.getGia()) + " VNĐ");
        holder.tvSoLuong.setText(gioHang.getSl() + "");

        int soLuong = Integer.parseInt(holder.tvSoLuong.getText().toString());
        checkSoLuong(soLuong, holder.btnCong, holder.btnTru);
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHang.setSl(gioHang.getSl() + 1);
                checkSoLuong(gioHang.getSl(), holder.btnCong, holder.btnTru);
                holder.tvSoLuong.setText(gioHang.getSl() + "");
                adapterCallback.onCallBack(gioHang);
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHang.setSl(gioHang.getSl() - 1);
                checkSoLuong(gioHang.getSl(), holder.btnCong, holder.btnTru);
                holder.tvSoLuong.setText(gioHang.getSl() + "");
                adapterCallback.onCallBack(gioHang);
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterCallback.onCallBack(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvHinh;
        public TextView tvTen, tvGia, tvSoLuong;
        public Button btnTru, btnCong, btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinh = (ImageView) itemView.findViewById(R.id.imvHinhGioHang);
            tvTen = (TextView) itemView.findViewById(R.id.tvTenSPGioHang);
            tvGia = (TextView) itemView.findViewById(R.id.tvGiaSpGioHang);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tvGioHangSoLuong);
            btnTru = (Button) itemView.findViewById(R.id.btnGioHangTru);
            btnCong = (Button) itemView.findViewById(R.id.btnGioHangCong);
            btnXoa = itemView.findViewById(R.id.xoa);
        }
    }
    public void checkSoLuong(int sl, Button btnCong, Button btnTru) {
        if (sl <= 1) {
            btnTru.setEnabled(false);
            btnCong.setEnabled(true);
        } else if (sl >= 1000) {
            btnCong.setEnabled(false);
            btnTru.setEnabled(true);
        } else {
            btnTru.setEnabled(true);
            btnCong.setEnabled(true);
        }
    }
}
