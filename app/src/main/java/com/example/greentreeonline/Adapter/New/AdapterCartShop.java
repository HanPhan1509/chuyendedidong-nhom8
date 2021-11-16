package com.example.greentreeonline.Adapter.New;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.AdapterCallback;
import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.CartShop;
import com.example.greentreeonline.Firebase.CartFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Main.MainCart;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class AdapterCartShop extends RecyclerView.Adapter<AdapterCartShop.ViewHolder> {
    private Context context;
    private ArrayList<CartShop> cartShops;
    private AdapterCallback adapterCallback;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String idUser;

    private CartFirebase cartFirebase;

    public AdapterCartShop(Context context, ArrayList<CartShop> cartShops, AdapterCallback adapterCallback){
        this.context = context;
        this.cartShops = cartShops;
        this.adapterCallback = adapterCallback;
        sharedPreferences = this.context.getSharedPreferences("luutaikhoan", this.context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        cartFirebase = new CartFirebase();

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_cartshop,null);

        return new AdapterCartShop.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AdapterCart adapterCart = new AdapterCart(context, cartShops.get(position).getCarts(), new AdapterCallback() {
            @Override
            public void onCallBack(Object obj) {
                if (obj instanceof Integer) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(position);
                    arrayList.add((int) obj);
                    adapterCallback.onCallBack(arrayList);
                }else{
                    adapterCallback.onCallBack(obj);
                }
            }
        });
        holder.id_shop.setText(cartShops.get(position).getIdshop());
        holder.rcv_cart.setHasFixedSize(true);
        holder.rcv_cart.setLayoutManager(new GridLayoutManager(context, 1));
        holder.rcv_cart.setAdapter(adapterCart);
    }

    @Override
    public int getItemCount() {
        return cartShops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_shop;
        RecyclerView rcv_cart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_shop = itemView.findViewById(R.id.id_shop);
            rcv_cart = itemView.findViewById(R.id.rcv_cart);
        }
    }
}
