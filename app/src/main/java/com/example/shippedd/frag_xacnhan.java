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
    ListView lvDonHangCho;
    ArrayList<Bill> list = new ArrayList<>();
    CustomAdapterChoXacNhan customAdapterChoXacNhan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_xacnhan, container, false);

        lvDonHangCho = view.findViewById(R.id.lvDonHangCho);

        customAdapterChoXacNhan =
                new CustomAdapterChoXacNhan(getContext(), R.layout.item_choxacnhan, list);
        lvDonHangCho.setAdapter(customAdapterChoXacNhan);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        // vào firebase lấy dữ lieu:
        list.clear();
        customAdapterChoXacNhan.notifyDataSetChanged();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 4) {
                    list.add(bill);
                    customAdapterChoXacNhan.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(snapshot.getKey())) {
                        Bill bill = snapshot.getValue(Bill.class);
                        if (bill.getStatus() == 4) {
                            list.add(bill);
                            customAdapterChoXacNhan.notifyDataSetChanged();
                            break;
                        } else {
                            list.remove(i);
                            customAdapterChoXacNhan.notifyDataSetChanged();
                            break;
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