package com.example.greentreeonline.Admin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapsanpham extends BaseAdapter {
    sanpham context;
    ArrayList<Product> listkh;



    public adapsanpham(sanpham context, ArrayList<Product> sp) {
        this.context = context;
        this.listkh =sp;
    }


    @Override
    public int getCount() {
        return listkh.size();
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
        public ImageView imvHinh;
        public TextView tvTen, tvGia;
        public Button xoa, sua;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemsanpham, null);

            holder.imvHinh = (ImageView) convertView.findViewById(R.id.adHinhsp);
            holder.tvTen = (TextView) convertView.findViewById(R.id.adTenSP);
            holder.tvGia = (TextView) convertView.findViewById(R.id.adGiaSp);
            holder.xoa = (Button) convertView.findViewById(R.id.xoa);
            holder.sua = (Button) convertView.findViewById(R.id.sua);
            final Product kh = listkh.get(position);

            holder.tvTen.setText(kh.getTensp());
            //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            holder.tvGia.setText((kh.getGia() + ""));
            Picasso.get().load(kh.getImgsp()).centerCrop().resize(150, 150).into(holder.imvHinh);
            holder.xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    xoa(kh.getTensp(), kh.getIdsp());

                }
            });
//
            convertView.setTag(holder);
        }

        return convertView;
    }


    public void xoa(String ten, final int id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Bạn có muốn xoá " + ten + " ?");

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                context.xoasanpham(id);
                context.getcay();
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


