package com.example.shippedd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Layer;

import com.example.shippedd.Bill;
import com.example.shippedd.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterChoXacNhan extends ArrayAdapter {


    Context context;
    int resource;
    ArrayList<Bill> data;

    public CustomAdapterChoXacNhan(Context context, int resource, ArrayList<Bill> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView txtmadoncxn = convertView.findViewById(R.id.txtmadoncxn);
        TextView ngaygiao = convertView.findViewById(R.id.ngaygiao);
        TextView nguoinhan = convertView.findViewById(R.id.nguoinhan);
        TextView sodienthoai = convertView.findViewById(R.id.sodienthoai);
        TextView diachi = convertView.findViewById(R.id.diachi);
        TextView phuongthuctt = convertView.findViewById(R.id.phuongthuctt);
        TextView tongsanpham = convertView.findViewById(R.id.tongsanpham);
        TextView tongtien = convertView.findViewById(R.id.tongtien);
        Button btnDaLayHang = convertView.findViewById(R.id.btnDaLayHang);


        Bill bill = data.get(position);

        txtmadoncxn.setText("Mã đơn hàng: " + bill.getId());
        ngaygiao.setText("Ngày giao: " + bill.getDate());
        nguoinhan.setText("Người nhận: " + bill.getTenkh());
        sodienthoai.setText("Số điện thoại: " + bill.getDt());
        diachi.setText("Địa chỉ: " + bill.getDiachi());
        phuongthuctt.setText("Phương thức thanh toán: Thanh toán khi nhận hàng");
        tongsanpham.setText("6 sản phẩm");
        tongtien.setText(bill.getTongtien() + "");


        btnDaLayHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // set lại trạng thái bill
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
                mDatabase.child(data.get(position).getId()).child("status").setValue(1);

                // set lại name trạng thái
                DatabaseReference mData = FirebaseDatabase.getInstance().getReference("bill");
                mData.child(data.get(position).getId()).child("namestatus").setValue("Đang giao");

            }
        });


        return convertView;
    }


    @Override
    public int getCount() {
        return data.size();
    }
}
