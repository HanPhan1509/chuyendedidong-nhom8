package com.example.greentreeonline.Adapter.Bill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greentreeonline.Class.Bill.InfoBill;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterInfoBill extends BaseAdapter {
    Context context;
    ArrayList<InfoBill> listGioHangAdapter;

    public AdapterInfoBill(Context context, ArrayList<InfoBill> listGioHangAdapter) {
        this.context = context;
        this.listGioHangAdapter = listGioHangAdapter;
    }

    @Override
    public int getCount() {
        return listGioHangAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    private class ViewHolder {
        private ImageView imvHinh;
        private TextView tvTen, tvGia, tvSoLuong;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemthanhtoan, null);
            holder = new ViewHolder();
            holder.imvHinh = (ImageView) convertView.findViewById(R.id.imvHinhGioHang);
            holder.tvTen = (TextView) convertView.findViewById(R.id.tvTenSPGioHang);
            holder.tvGia = (TextView) convertView.findViewById(R.id.tvGiaSpGioHang);
            holder.tvSoLuong = (TextView) convertView.findViewById(R.id.tvGioHangSoLuong);
            //holder.btnTru = (Button) convertView.findViewById(R.id.btnGioHangTru);

            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();
        final InfoBill gioHang = listGioHangAdapter.get(position);
        Picasso.get().load(gioHang.getImgsp()).into(holder.imvHinh);
        holder.tvTen.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText(decimalFormat.format(gioHang.getGiasp()) + " VNĐ");
        holder.tvSoLuong.setText(gioHang.getSluong() + "");


        return convertView;

    }
}

