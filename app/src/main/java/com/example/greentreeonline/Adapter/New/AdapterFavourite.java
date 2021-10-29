package com.example.greentreeonline.Adapter.New;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Class.New.Favourite;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.ViewHoler> {
    Context context;
    ArrayList<Favourite> favouriteArrayList;
    String urlImage = "https://firebasestorage.googleapis.com/v0/b/greentreeonline-9eb47.appspot.com/o/";
    String duoiimg = "?alt=media";

    public AdapterFavourite(Context context, ArrayList<Favourite> favouriteArrayList) {
        this.context = context;
        this.favouriteArrayList = favouriteArrayList;
    }

    @NonNull
    @Override
    public AdapterFavourite.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.itemlike,null);
        return new AdapterFavourite.ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavourite.ViewHoler holder, int position) {
        Favourite favourite = favouriteArrayList.get(position);
        Picasso.get().load(urlImage + favourite.getImgsp() + duoiimg).into(holder.imageView);
        holder.tvTen.setText(favourite.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("#########");
        holder.tvgia.setText(decimalFormat.format(favourite.getGia()));
    }

    @Override
    public int getItemCount() {
        return favouriteArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTen, tvgia;
        Button bt;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imvlike);
            tvTen = (TextView) itemView.findViewById(R.id.tvtenlike);
            tvgia = (TextView) itemView.findViewById(R.id.tvgialike);
        }
    }
}
