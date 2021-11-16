package com.example.shippedd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shippedd.adapter.CustomAdapterChoXacNhan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class
frag_xacnhan extends Fragment {

    ArrayList<Bill> xacnhan = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_xacnhan, container, false);

        ListView lvDonHangCho = view.findViewById(R.id.lvDonHangCho);
        ArrayList<Bill> list = new ArrayList<>();
        CustomAdapterChoXacNhan customAdapterChoXacNhan =
                new CustomAdapterChoXacNhan(getContext(), R.layout.item_choxacnhan, list);
        lvDonHangCho.setAdapter(customAdapterChoXacNhan);


        // vào firebase lấy dữ lieu:
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 0) {
                    list.add(bill);
                    customAdapterChoXacNhan.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(snapshot.getKey())) {
                        list.remove(i);

                    }
                }
                removeindex();
            }
            public void removeindex() {
                for (int j = 0; j < xacnhan.size(); j++) {
                    if (xacnhan.get(j).getStatus() != 0) {
                        xacnhan.remove(j);

                    }
                }
                customAdapterChoXacNhan.notifyDataSetChanged();
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


        return view;

    }

}