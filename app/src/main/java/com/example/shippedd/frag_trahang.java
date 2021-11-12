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
import com.example.shippedd.adapter.CustomAdapterTraHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class frag_trahang extends Fragment {
    ListView lvtrahang;
    CustomAdapterTraHang customAdapterTraHang;
    ArrayList<Bill> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_trahang, container, false);

         lvtrahang = view.findViewById(R.id.lvtrahang);

         customAdapterTraHang =
                new CustomAdapterTraHang(getContext(), R.layout.itemtrahang, list);
        lvtrahang.setAdapter(customAdapterTraHang);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
        mDatabase.addChildEventListener(new ChildEventListener()
        {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 3) {
                    list.add(bill);
                    customAdapterTraHang.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(snapshot.getKey())) {
                        Bill bill = snapshot.getValue(Bill.class);
                        if(bill.getStatus()==3)
                        {
                            list.add(bill);
                            customAdapterTraHang.notifyDataSetChanged();
                        }
                        else {
                            list.remove(i);
                            customAdapterTraHang.notifyDataSetChanged();
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

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        customAdapterTraHang.notifyDataSetChanged();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bill");
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 3) {
                    list.add(bill);
                    customAdapterTraHang.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(snapshot.getKey())) {
                        Bill bill = snapshot.getValue(Bill.class);
                        if(bill.getStatus()==3)
                        {
                            list.add(bill);
                            customAdapterTraHang.notifyDataSetChanged();
                        }
                        else {
                            list.remove(i);
                            customAdapterTraHang.notifyDataSetChanged();
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