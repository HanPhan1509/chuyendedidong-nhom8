package com.example.shippedd.adapter;

import android.content.Context;
import android.security.identity.EphemeralPublicKeyNotFoundException;
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

public class CustomAdapterTraHang extends ArrayAdapter
{
    Context context;
    int resource;
    ArrayList<Bill> data;

    public CustomAdapterTraHang(Context context, int resource, ArrayList<Bill> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView txtmadoncxn3 = convertView.findViewById(R.id.txtmadoncxn3);
        TextView ngaygiao3 = convertView.findViewById(R.id.ngaygiao3);
        TextView nguoinhan3 = convertView.findViewById(R.id.nguoinhan3);
        TextView sodienthoai3 = convertView.findViewById(R.id.sodienthoai3);
        TextView diachi3 = convertView.findViewById(R.id.diachi3);
        TextView phuongthuctt3 = convertView.findViewById(R.id.phuongthuctt3);
        TextView tongsanpham3 = convertView.findViewById(R.id.tongsanpham3);
        TextView tongtien3 = convertView.findViewById(R.id.tongtien3);
        TextView txthienthi1 = convertView.findViewById(R.id.txthienthi1);



        Bill bill = data.get(position);

        txtmadoncxn3.setText("Mã đơn hàng: " + bill.getId());
        ngaygiao3.setText("Ngày giao: " + bill.getDate());
        nguoinhan3.setText("Người nhận: " + bill.getTenkh());
        sodienthoai3.setText("Số điện thoại: " + bill.getDt());
        diachi3.setText("Địa chỉ: " + bill.getDiachi());
        phuongthuctt3.setText("Phương thức thanh toán: Thanh toán khi nhận hàng");

        tongtien3.setText(bill.getTongtien() + "");
        txthienthi1.setText("Giao hàng thất bại");



        return convertView;
    }
    public int getCount() {
        return data.size();
    }
}
