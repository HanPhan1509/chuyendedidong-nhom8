package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.Class.Category.CategoryChild;
import com.example.greentreeonline.Class.User.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryFireBase {
    //    Connect database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private boolean check = false;

    public CategoryFireBase() {

    }


    public void getAllCategory(FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("category")
                .orderByChild("name");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Category> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Category category = data.getValue(Category.class);
                        if(category != null){
                            categoryArrayList.add(category);
                        }
                    }
                }
                firebaseCallback.onCallBack(categoryArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                        check = false;
            }
        });
    }

    public void getCategoryChild(String idCat, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("categorychild")
                .orderByChild("idcat")
                .equalTo(idCat);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<CategoryChild> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        CategoryChild categoryChild = data.getValue(CategoryChild.class);
                        if(categoryChild != null){
                            categoryArrayList.add(categoryChild);
                        }
                    }
                }
                firebaseCallback.onCallBack(categoryArrayList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//               check = false;
            }
        });
    }

}
