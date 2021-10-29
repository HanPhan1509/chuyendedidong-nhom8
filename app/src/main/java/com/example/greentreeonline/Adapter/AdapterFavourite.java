package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greentreeonline.Class.Favourite;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterFavourite extends BaseAdapter { ;
    Context context;
    ArrayList<Favourite> listyt;


    public AdapterFavourite(Context context, ArrayList<Favourite> yt) {
        this.context = context;
        this.listyt = yt;
    }


    @Override
    public int getCount() {
        return listyt.size();
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
        TextView tvTen, tvgia;
        Button bt;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemlike, null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.imvlike);
            holder.tvTen = (TextView) convertView.findViewById(R.id.tvtenlike);
            holder.tvgia = (TextView) convertView.findViewById(R.id.tvgialike);

            Favourite yt = listyt.get(position);
            Picasso.get().load(yt.getImgsp()).into(holder.imageView);
            holder.tvTen.setText(yt.getTensp());
            DecimalFormat decimalFormat = new DecimalFormat("#########");
            holder.tvgia.setText(decimalFormat.format(yt.getGt()));


            convertView.setTag(holder);

        }
        return convertView;


    }
}
