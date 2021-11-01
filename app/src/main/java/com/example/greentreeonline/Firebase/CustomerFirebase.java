package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.Customer;
import com.example.greentreeonline.Class.New.Favourite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public CustomerFirebase(){

    }

    public void addCustomer(Customer customer, FirebaseCallback firebaseCallback){
        String customerID = myRef.child("customer").push().getKey();
        customer.setId(customerID);
        myRef.child("customer").child(customerID).setValue(customer);
        myRef.child("customer").child(customerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Customer favourite1 = dataSnapshot.getValue(Customer.class);
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

//    public void deleteFavourite(String iduser, String idsp, FirebaseCallback firebaseCallback){
//        myRef
//                .child("favourite")
//                .child(iduser+idsp)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists()) {
//                            snapshot.getRef().removeValue();
//                            firebaseCallback.onCallBack(true);
//                            return;
//                        }
//                        firebaseCallback.onCallBack(false);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        firebaseCallback.onCallBack(false);
//                    }
//                });
//    }

    public void getCustomerUserId(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("customer")
                .orderByChild("iduser")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Customer> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Customer product = data.getValue(Customer.class);
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
