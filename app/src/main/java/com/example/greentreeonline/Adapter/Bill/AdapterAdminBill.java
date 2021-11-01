package com.example.greentreeonline.Adapter.Bill;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;


import com.example.greentreeonline.Admin.donhangadmin;
import com.example.greentreeonline.Class.New.Bill;
import com.example.greentreeonline.Main.Bill.MainBill;
import com.example.greentreeonline.Main.Bill.MainInfoBill;
import com.example.greentreeonline.Main.Bill.MainStatusBill;
import com.example.greentreeonline.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.greentreeonline.Fragment.FragmentHome.objdh;


public class AdapterAdminBill extends BaseAdapter {
    Context context;
    ArrayList<Bill> listdonhang;

    public AdapterAdminBill(Context context, ArrayList<Bill> objdh) {
        this.context = context;
        this.listdonhang = objdh;
    }

    @Override
    public int getCount() {
        return listdonhang.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView madh, tenkh, dckh, tongt, tt,date;
        TextView huy;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_orderinfo, null);

            viewHolder.madh = (TextView) convertView.findViewById(R.id.tv_iddh);
            viewHolder.tenkh = (TextView) convertView.findViewById(R.id.tv_hitory_product_name);
            viewHolder.dckh = (TextView) convertView.findViewById(R.id.tv_hitory_product_num);
            viewHolder.tongt = (TextView) convertView.findViewById(R.id.tv_hitory_product_price);
            viewHolder.tt = (TextView) convertView.findViewById(R.id.tv_hitory_product_status);
            viewHolder.date=(TextView) convertView.findViewById(R.id.tv_hitory_product_date);
            viewHolder.huy = (TextView) convertView.findViewById(R.id.txthuydonhang);
            final Bill kh = listdonhang.get(position);

            viewHolder.madh.setText(kh.getId() + "");
            viewHolder.tenkh.setText(kh.getTenkh());
            viewHolder.dckh.setText(kh.getDiachi());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            viewHolder.tongt.setText(decimalFormat.format(kh.getTongtien()) + " VND");
            viewHolder.date.setText(kh.getDate());
            viewHolder.tt.setText(kh.getNamestatus());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainStatusBill.class);
                    intent.putExtra("madonhang",listdonhang.get(position).getId() + "");
                    intent.putExtra("sdt", "0" + listdonhang.get(position).getDt() + "");
                    intent.putExtra("tenkh", listdonhang.get(position).getTenkh().toString());
                    intent.putExtra("diachi", listdonhang.get(position).getDiachi().toString());
                    intent.putExtra("ngay", listdonhang.get(position).getDate().toString());
                    intent.putExtra("phiship", listdonhang.get(position).getPhiship() + "đ");
                    intent.putExtra("tamtinh", listdonhang.get(position).getTamtinh() + "đ");
                    intent.putExtra("tongtien", listdonhang.get(position).getTongtien() + "đ");
                    intent.putExtra("trangthai", " Đơn hàng " + listdonhang.get(position).getNamestatus().toString());
                    intent.putExtra("status", kh.getStatus() + "");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            viewHolder.huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    huy(kh.getId());

                }
            });

            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    public void huy(String id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Bạn có hủy đơn hàng : " + id + " ?");

        builder.setNegativeButton(" Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                context.huydonhang(id);
//                context.getdonhang();
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

