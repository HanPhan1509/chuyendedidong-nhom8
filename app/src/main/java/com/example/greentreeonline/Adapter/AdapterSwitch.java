package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greentreeonline.R;

public class AdapterSwitch extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;

    public AdapterSwitch(Context applicationContext, int[] flags, String[] countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView tvTen, tvgia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemvanchuyen, null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.imvHinhdv);
            holder.tvTen = (TextView) convertView.findViewById(R.id.tvTendonvi);
          //  holder.tvgia = (TextView) convertView.findViewById(R.id.tvphi);
           holder.imageView.setImageResource(flags[position]);
            holder.tvTen.setText(countryNames[position]);
         //   objgiohang kh = listvc.get(position);

           // holder.tvTen.setText(kh.getTen());
       //   DecimalFormat decimalFormat = new DecimalFormat("#########");
          //  holder.tvgia.setText( kh.getPhiship()+" đ");

         //   Picasso.get().load(kh.getHinh()).into(holder.imageView);

            convertView.setTag(holder);
        }

        return convertView;
    }

}
