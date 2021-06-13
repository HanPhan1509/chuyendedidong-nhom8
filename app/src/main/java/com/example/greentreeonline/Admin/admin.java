package com.example.greentreeonline.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.greentreeonline.Main.Bill.MainStatusBill;
import com.example.greentreeonline.R;

public class admin extends Fragment {

    Button tk, sp,hd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin, container, false);
        tk = view.findViewById(R.id.thongke);
        sp = view.findViewById(R.id.adsanpham);
        hd= view.findViewById(R.id.hoadon);
        click();
        return view;

    }

    public void click() {
        tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), thongke.class);
                startActivity(intent);
            }
        });
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), sanpham.class);
                startActivity(intent);
            }
        });
        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainStatusBill.class);
                startActivity(intent);
            }
        });
    }
}