package com.example.shippedd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shippedd.adapter.CustomAdapterDaNhan;
import com.example.shippedd.adapter.CustomAdapterShop;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class frag_xacnhan_shop extends Fragment {
    ListView lvdanhan;
    ArrayList<Bill> list = new ArrayList<>();
    CustomAdapterShop customAdapterDaNhan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_xacnhan_shop, container, false);

        lvdanhan = view.findViewById(R.id.lvdanhan);
        customAdapterDaNhan = new CustomAdapterShop(getContext(), R.layout.item_danhan, list);
        lvdanhan.setAdapter(customAdapterDaNhan);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");

        return view;
    }

    public void onResume() {
        super.onResume();
        list.clear();
        customAdapterDaNhan.notifyDataSetChanged();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
        mDatabase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 0) {
                    list.add(bill);
                    customAdapterDaNhan.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(snapshot.getKey())) {
                        Bill bill = snapshot.getValue(Bill.class);
                        if (bill.getStatus() == 0) {
                            list.add(bill);
                            customAdapterDaNhan.notifyDataSetChanged();
                        } else {
                            list.remove(i);
                            customAdapterDaNhan.notifyDataSetChanged();
                        }

                    }

                }


            }


            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}