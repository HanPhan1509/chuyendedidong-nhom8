package com.example.greentreeonline.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class ArrayAdapterCategory extends ArrayAdapter<Category>{
    private Context context;
    private ArrayList<Category> statuses;
    public Resources res;
    Category currRowVal = null;
    LayoutInflater inflater;
    AdapterCallback adapterCallback;

    public ArrayAdapterCategory(Context context,
                         int textViewResourceId, ArrayList<Category> statuses,
                         Resources resLocal, AdapterCallback adapterCallback) {
        super(context, textViewResourceId, statuses);
        this.context = context;
        this.statuses = statuses;
        this.res = resLocal;
        this.adapterCallback = adapterCallback;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent, adapterCallback);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        adapterCallback.onCallBack(statuses.get(position).getId());
        return getCustomView(position, convertView, parent, adapterCallback);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent, AdapterCallback adapterCallback) {
        View row = inflater.inflate(R.layout.item_cat, parent, false);
        currRowVal = null;
        currRowVal = (Category) statuses.get(position);
        TextView label = (TextView) row.findViewById(R.id.name);
        label.setText(statuses.get(position).getName());
        this.adapterCallback = adapterCallback;
        return row;
    }

}
