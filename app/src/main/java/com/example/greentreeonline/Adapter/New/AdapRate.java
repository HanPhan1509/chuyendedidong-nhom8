package com.example.greentreeonline.Adapter.New;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Class.New.Rate;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class AdapRate extends RecyclerView.Adapter<AdapRate.ViewHolder> {
    Context context;
    ArrayList<Rate> rates;

    public AdapRate(Context context, ArrayList<Rate> rates){
        this.context = context;
        this.rates = rates;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_rate,null);
        return new AdapRate.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rate rate = rates.get(position);
        holder.tennv.setText(rate.getNameuser());
        holder.danhgia.setText(rate.getDanhgia());
        holder.ratingBar.setRating(rate.getStar());
    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView tennv, danhgia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tennv = itemView.findViewById(R.id.tennv);
            danhgia = itemView.findViewById(R.id.danhgia);

        }
    }
}
