package com.example.shippedd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shippedd.Bill;
import com.example.shippedd.R;

import java.util.ArrayList;

public class CustomAdapterShop extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Bill> data;

    public CustomAdapterShop(Context context, int resource, ArrayList<Bill> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView txtmadoncxn2 = convertView.findViewById(R.id.txtmadoncxn2);
        TextView ngaygiao2 = convertView.findViewById(R.id.ngaygiao2);
        TextView nguoinhan2 = convertView.findViewById(R.id.nguoinhan2);
        TextView sodienthoai2 = convertView.findViewById(R.id.sodienthoai2);
        TextView diachi2 = convertView.findViewById(R.id.diachi2);
        TextView phuongthuctt2 = convertView.findViewById(R.id.phuongthuctt2);
        TextView tongsanpham2 = convertView.findViewById(R.id.tongsanpham2);
        TextView tongtien2 = convertView.findViewById(R.id.tongtien2);
        TextView txthienthi = convertView.findViewById(R.id.txthienthi);


        Bill bill = data.get(position);

        txtmadoncxn2.setText("Mã đơn hàng: " + bill.getId());
        ngaygiao2.setText("Ngày giao: " + bill.getDate());
        nguoinhan2.setText("Người nhận: " + bill.getTenkh());
        sodienthoai2.setText("Số điện thoại: " + bill.getDt());
        diachi2.setText("Địa chỉ: " + bill.getDiachi());
        phuongthuctt2.setText("Phương thức thanh toán: Thanh toán khi nhận hàng");

        tongtien2.setText(bill.getTongtien() + "");
        txthienthi.setText("Có đơn hàng");

        return convertView;
    }

    public int getCount() {
        return data.size();
    }
}
