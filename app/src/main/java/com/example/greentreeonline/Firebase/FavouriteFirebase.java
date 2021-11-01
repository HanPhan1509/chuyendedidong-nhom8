package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Favourite;
import com.example.greentreeonline.Class.New.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavouriteFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public FavouriteFirebase(){

    }

    public void addFavourite(Favourite favourite, FirebaseCallback firebaseCallback){
        String favouriteID = favourite.getIduser()+favourite.getIdsp();
        myRef.child("favourite").child(favouriteID).setValue(favourite);
        myRef.child("favourite").child(favouriteID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Favourite favourite1 = dataSnapshot.getValue(Favourite.class);
                boolean check;
                if (favourite1 != null) {
                    check = true;
                } else {
                    check = false;
                }
                firebaseCallback.onCallBack(check);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void deleteFavourite(String iduser, String idsp, FirebaseCallback firebaseCallback){
        myRef
                .child("favourite")
                .child(iduser+idsp)
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    snapshot.getRef().removeValue();
                    firebaseCallback.onCallBack(true);
                    return;
                }
                firebaseCallback.onCallBack(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void getFavouriteUserId(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("favourite")
                .orderByChild("iduser")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Favourite> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Favourite product = data.getValue(Favourite.class);
                        if (product != null) {
                            categoryArrayList.add(product);
                        }
                    }
                }
                firebaseCallback.onCallBack(categoryArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseCallback.onCallBack(null);
            }
        });
    }
}
