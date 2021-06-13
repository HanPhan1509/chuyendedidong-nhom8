package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greentreeonline.Class.Customer;
import com.example.greentreeonline.Class.Switch;
import com.example.greentreeonline.Main.Oder.MainSwitch;
import com.example.greentreeonline.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterCustomer extends BaseAdapter {
    Context context;
    ArrayList<Customer> listkh;

    public AdapterCustomer(Context context, ArrayList<Customer> listkhadap) {
        this.context = context;
        this.listkh = listkhadap;
    }

    public AdapterCustomer(MainSwitch context, ArrayList<Switch> vc) {

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

    private class ViewHolder {
        ImageView imageView;
        TextView tvTen, tvsdt, tvdiachi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemkhachhang, null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.lotical);
            holder.tvTen = (TextView) convertView.findViewById(R.id.tv_tenkh);
            holder.tvsdt = (TextView) convertView.findViewById(R.id.tv_sdtkh);
            holder.tvdiachi = (TextView) convertView.findViewById(R.id.diachikh);
            Customer kh = listkh.get(position);

            holder.tvTen.setText(kh.getHoten());
            DecimalFormat decimalFormat = new DecimalFormat("#########");
            holder.tvsdt.setText("+84" + decimalFormat.format(kh.getSdt()));
            holder.tvdiachi.setText(kh.getDiachi());

            convertView.setTag(holder);
        }
        return convertView;
    }
}
