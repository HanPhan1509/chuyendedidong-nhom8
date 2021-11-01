package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Staff;
import com.example.greentreeonline.Class.User.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StaffFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public StaffFirebase(){

    }

    public void addStaff(Staff staff, FirebaseCallback firebaseCallback) {
        String userID = myRef.child("staff").push().getKey();
        staff.setIdnv(userID);
        myRef.child("staff").child(userID).setValue(staff);
        myRef.child("staff").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Staff user1 = dataSnapshot.getValue(Staff.class);
                // ..
                if (user1 != null) {
                    firebaseCallback.onCallBack(true);
                } else {
                    firebaseCallback.onCallBack(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void getStaffByIdOwner(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("staff")
                .orderByChild("idowner")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Staff> staffArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Staff staff = data.getValue(Staff.class);
                        staffArrayList.add(staff);
                    }
                }
                firebaseCallback.onCallBack(staffArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseCallback.onCallBack(null);
            }
        });
    }

    public void removeStaff(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("staff")
                .orderByChild("idnv")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dataSnapshot.getRef().removeValue();
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

    public void updateStaff(Staff staff, FirebaseCallback firebaseCallback){
        myRef.child("staff").child(staff.getIdnv()).setValue(staff);
        myRef.child("staff").child(staff.getIdnv()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Staff staff1 = snapshot.getValue(Staff.class);
                if(staff1.equals(staff)){
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

}
