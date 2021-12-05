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

import com.example.shippedd.Bill;
import com.example.shippedd.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapterDangGiao extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Bill> data;

    public CustomAdapterDangGiao(Context context, int resource, ArrayList<Bill> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView txtmadoncxn1 = convertView.findViewById(R.id.txtmadoncxn1);
        TextView ngaygiao1 = convertView.findViewById(R.id.ngaygiao1);
        TextView nguoinhan1 = convertView.findViewById(R.id.nguoinhan1);
        TextView sodienthoai1 = convertView.findViewById(R.id.sodienthoai1);
        TextView diachi1 = convertView.findViewById(R.id.diachi1);
        TextView phuongthuctt1 = convertView.findViewById(R.id.phuongthuctt1);
        TextView tongsanpham1 = convertView.findViewById(R.id.tongsanpham1);
        TextView tongtien1 = convertView.findViewById(R.id.tongtien1);
        Button btnhuy = convertView.findViewById(R.id.btnhuy);
        Button btnxong = convertView.findViewById(R.id.btnxong);


        Bill bill = data.get(position);

        txtmadoncxn1.setText("Mã đơn hàng: " + bill.getId());
        ngaygiao1.setText("Ngày giao: " + bill.getDate());
        nguoinhan1.setText("Người nhận: " + bill.getTenkh());
        sodienthoai1.setText("Số điện thoại: " + bill.getDt());
        diachi1.setText("Địa chỉ: " + bill.getDiachi());
        phuongthuctt1.setText("Phương thức thanh toán: Thanh toán khi nhận hàng");

        tongtien1.setText(bill.getTongtien() + "");
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
                mDatabase.child(data.get(position).getId()).child("status").setValue(3);

                DatabaseReference mData = FirebaseDatabase.getInstance().getReference("bill");
                mData.child(data.get(position).getId()).child("namestatus").setValue("đã hủy");

            }
        });
        btnxong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
                mDatabase.child(data.get(position).getId()).child("status").setValue(2);

                // set lại name trạng thái
                DatabaseReference mData = FirebaseDatabase.getInstance().getReference("bill");
                mData.child(data.get(position).getId()).child("namestatus").setValue("đã nhận");

            }

        });





        return convertView;
    }
    public int getCount() {
        return data.size();
    }
}
