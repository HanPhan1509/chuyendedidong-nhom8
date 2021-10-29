package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Rate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RateFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public RateFirebase() {

    }

    public void addRate(Rate rate, FirebaseCallback firebaseCallback){
        String rateId = myRef.child("rate").push().getKey();
        myRef.child("rate").child(rateId).setValue(rate);
        myRef.child("rate").child(rateId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Rate rate1 = snapshot.getValue(Rate.class);
                if(rate1 != null){
                    firebaseCallback.onCallBack(true);
                }else {
                    firebaseCallback.onCallBack(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void getRate(String idsp, FirebaseCallback firebaseCallback){
        Query query = myRef
                        .child("rate")
                        .orderByChild("idsp")
                        .equalTo(idsp);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Rate> rates = new ArrayList<>();
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Rate rate = dataSnapshot.getValue(Rate.class);
                        rates.add(rate);
                    }
                }
                firebaseCallback.onCallBack(rates);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseCallback.onCallBack(null);
            }
        });
    }

}
