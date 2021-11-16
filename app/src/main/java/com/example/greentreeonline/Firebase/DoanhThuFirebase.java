package com.example.greentreeonline.Firebase;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Bill;
import com.example.greentreeonline.Class.New.DoanhThu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class DoanhThuFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public DoanhThuFirebase(){

    }

    public void addDoanhThu(String idShop, int sotien, FirebaseCallback firebaseCallback){
        myRef.child("doanhthu").child(idShop).child(year()).child(month() +"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DoanhThu doanhThu1 = snapshot.getValue(DoanhThu.class);
                if(doanhThu1 == null){
                    DoanhThu doanhThu = new DoanhThu(month(), sotien, idShop);
                    updateDoanhThu(doanhThu, firebaseCallback);
                }else{
                    doanhThu1.setDoanhthu(doanhThu1.getDoanhthu() + sotien);
                    updateDoanhThu(doanhThu1, firebaseCallback);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseCallback.onCallBack(false);
            }
        });
    }
    public void updateDoanhThu(DoanhThu doanhThu, FirebaseCallback firebaseCallback){
        myRef.child("doanhthu").child(doanhThu.getIdshop()).child(year()).child(month() +"").setValue(doanhThu);
        myRef.child("doanhthu").child(doanhThu.getIdshop()).child(year()).child(month() +"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DoanhThu doanhThu1 = snapshot.getValue(DoanhThu.class);
                if(doanhThu1 != null){
                    firebaseCallback.onCallBack(true);
                }else{
                    firebaseCallback.onCallBack(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void GetDoanhThuByIdShop(String idshop, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("doanhthu")
                .child(idshop)
                .child(year());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DoanhThu> doanhThus = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    DoanhThu product = data.getValue(DoanhThu.class);
                    if (product != null) {
                        doanhThus.add(product);
                    }
                }
                firebaseCallback.onCallBack(doanhThus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseCallback.onCallBack(null);
            }
        });
    }


    private int month() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(calendar.MONTH) + 1;
        return month;
    }

    private String year(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        return year + "";
    }
}
