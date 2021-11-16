package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

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

public class UserFireBase {
    //    Connect database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private boolean check = false;
    private User ownuser;

    public UserFireBase() {

    }

    //    kiểm tra có tồn tại sđt trong database chưa
    public boolean checkExistSDT(String sdt, FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("users")
                .orderByChild("sdt")
                .equalTo(sdt);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    check = true;
                } else {
                    check = false;
                }
                firebaseCallback.onCallBack(check);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
        return this.check;
    }

    //    kiểm tra có tồn tại gmail trong database chưa
    public boolean checkExistGmail(String gmail, FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("users")
                .orderByChild("gmail")
                .equalTo(gmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    //gmail found
                    check = true;
                } else {
                    //gmail not found
                    check = false;
                }
                firebaseCallback.onCallBack(check);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return check;
    }

    public boolean addUser(User user, FirebaseCallback firebaseCallback) {
        String userID = myRef.child("users").push().getKey();
        user.setId(userID);
        myRef.child("users").child(userID).setValue(user);
        myRef.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user1 = dataSnapshot.getValue(User.class);
                // ..
                if (user1 != null) {
                    check = true;
                } else {
                    check = false;
                }
                firebaseCallback.onCallBack(check);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                check = false;
            }
        });
        return check;
    }

    public User getUser(String userID) {
        myRef.child("users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user1 = dataSnapshot.getValue(User.class);
                // ..
                if (user1 != null) {
                    ownuser = new User(user1);
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });
        return ownuser;
    }

    public void login(String email, String password, FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("users")
                .orderByChild("gmail")
                .equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        User user = data.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            ownuser = new User(user);
                        } else {
                            ownuser = null;
                        }

                    }
                } else {
                    ownuser = null;
                }
                firebaseCallback.onCallBack(ownuser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                        check = false;
            }
        });
    }

    public void update(String id, User user, FirebaseCallback firebaseCallback){
        myRef.child("users").child(id).setValue(user)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    firebaseCallback.onCallBack(true);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    firebaseCallback.onCallBack(false);
                }
            });

    }

    public void resetPassword(String email, String password, FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("users")
                .orderByChild("gmail")
                .equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        User user = data.getValue(User.class);
                        System.out.println(user.getId());
                        if (user != null) {
                            ownuser = new User(user);
                            ownuser.setPassword(password);
                            update(user.getId(), ownuser, new FirebaseCallback() {
                                @Override
                                public void onCallBack(Object obj) {
                                    if((boolean) obj == true){
                                        firebaseCallback.onCallBack(true);
                                    }else{
                                        firebaseCallback.onCallBack(false);
                                    }
                                }
                            });
                        } else {
                            firebaseCallback.onCallBack(false);
                        }
                    }
                } else {
                    firebaseCallback.onCallBack(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                        check = false;
            }
        });
    }

//    String userId = myRef.push().getKey();
//        Category category = new Category(userId, "Cây cảnh");
//        myRef.child(userId).setValue(category);
//
//        ArrayList<String> categoryChild = new ArrayList<String>();
//        categoryChild.add("Cây nội thất – Cây Văn Phòng");
//        categoryChild.add("Cây trồng trong nhà");
//        categoryChild.add("Cây phong thủy");
//        categoryChild.add("Cây để bàn");
//        categoryChild.add("Cây chậu treo");
//        categoryChild.add("Cây không khí");
//        categoryChild.add("Cây trồng trong nước");
//        categoryChild.add("Bonsai – cây cảnh");
//        categoryChild.add("Cây cảnh nghệ thuật");
//
//        for (int i = 0; i < categoryChild.size(); i++){
//            myRef = database.getReference("categorychild");
//            String catchildID = myRef.push().getKey();
//            CategoryChild catchild = new CategoryChild(catchildID, categoryChild.get(i), userId);
//            myRef.child(catchildID).setValue(catchild);
//        }
}
