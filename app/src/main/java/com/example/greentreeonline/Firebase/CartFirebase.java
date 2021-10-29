package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.CartShop;
import com.example.greentreeonline.Class.New.Favourite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public CartFirebase() {

    }

    public void addCart(Cart favourite, FirebaseCallback firebaseCallback) {
        String favouriteID = favourite.getIdshop();
        String cartID = favourite.getIduser();
        myRef.child("cart").child(cartID).child(favouriteID).child(favourite.getIdsp()).setValue(favourite);
        myRef.child("cart").child(cartID).child(favouriteID).child(favourite.getIdsp()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Cart favourite1 = dataSnapshot.getValue(Cart.class);
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

    public void deleteCart(String iduser, String idshop, String idsp, FirebaseCallback firebaseCallback) {
        myRef
                .child("cart")
                .child(iduser)
                .child(idshop)
                .child(idsp)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
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

    public void deleteCartUserId(String id, String idshop, FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("cart")
                .child(id)
                .child(idshop);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        data.getRef().removeValue();
                    }
                    firebaseCallback.onCallBack(true);
                }
                firebaseCallback.onCallBack(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void getCartUserId(String id, FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("cart")
                .child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<CartShop> categoryArrayList = new ArrayList<>();
                GenericTypeIndicator<HashMap<String, Cart>> t = new GenericTypeIndicator<HashMap<String, Cart>>() {
                };
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        ArrayList<Cart> carts = new ArrayList<>();
                        HashMap<String, Cart> haha = data.getValue(t);
                        for (Map.Entry<String, Cart> entry : haha.entrySet()) {
                            Cart cart = entry.getValue();
                            carts.add(cart);
                        }
                        CartShop cartShop = new CartShop(data.getKey(), carts);
                        categoryArrayList.add(cartShop);
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
