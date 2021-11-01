package com.example.greentreeonline.Adapter.New;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.AdapterOder;
import com.example.greentreeonline.Adapter.AdapterSwitch;
import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.CartShop;
import com.example.greentreeonline.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterBill extends RecyclerView.Adapter<AdapterBill.ViewHolder> {
    private Context context;
    private ArrayList<CartShop> cartShops;

    public AdapterBill(Context context, ArrayList<CartShop> cartShops){
        this.context = context;
        this.cartShops = cartShops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_donhang,null);
        return new AdapterBill.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartShop cartShop = cartShops.get(position);
        holder.id_shop.setText(cartShop.getIdshop());
        holder.tamtinh.setText(tongTien(cartShop.getCarts()) + "đ");
        AdapterOder adapterGioHang = new AdapterOder(context, cartShop.getCarts());
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.listView.getLayoutParams();
        lp.height = 350 * cartShop.getCarts().size();
        holder.listView.setLayoutParams(lp);
        holder.listView.setAdapter(adapterGioHang);
    }

    @Override
    public int getItemCount() {
        return cartShops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListView listView;
        public TextView tendv, date, chon, id_shop;
        public TextView phiship, ten, tamtinh;
        public Spinner spinner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tendv = itemView.findViewById(R.id.ten);
            tamtinh = itemView.findViewById(R.id.tamtinh);
            phiship = itemView.findViewById(R.id.tvphisip);
            chon = itemView.findViewById(R.id.chondv);
            spinner = itemView.findViewById(R.id.vanchuyen);
            listView = itemView.findViewById(R.id.lvthanhtoan);
            date = itemView.findViewById(R.id.date);
            id_shop = itemView.findViewById(R.id.id_shop);

            String[] dcs = {"Ninja Van", "Viettel Express", "Grap Express", "NowShip"};
            int flags[] = {R.drawable.ninjavan, R.drawable.viettel, R.drawable.grap, R.drawable.now};
            AdapterSwitch vc = new AdapterSwitch(context, flags, dcs);
            spinner.setAdapter(vc);
            date(date);

        }
    }
    private void date(TextView date) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH) + 1;
        int day = calendar.get(calendar.DAY_OF_MONTH);
        date.setText(new StringBuffer().append(year).append("-").append(month).append("-").append(day));
    }

    public int tongTien(ArrayList<Cart> carts){
        int gia = 0;
        for(Cart cart: carts){
            gia += cart.tongTien();
        }
        return gia;
    }
}
