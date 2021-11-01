package com.example.greentreeonline.Firebase;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ProductFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private FirebaseStorage storage = FirebaseStorage.getInstance("gs://greentreeonline-9eb47.appspot.com/");
    private StorageReference storageRef = storage.getReference();

    public ProductFirebase() {

    }

    public void addProduct(Product product, FirebaseCallback firebaseCallback) {
        String productID = myRef.child("products").push().getKey();
        product.setIdsp(productID);
        myRef.child("products").child(productID).setValue(product);
        myRef.child("products").child(productID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product product1 = dataSnapshot.getValue(Product.class);
                boolean check;
                if (product != null) {
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

    public void getAllProduct(FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("products")
                .orderByChild("status")
                .equalTo(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Product> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Product product = data.getValue(Product.class);
                        if (product != null) {
                            categoryArrayList.add(product);
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

    public void removeProduct(Product product, FirebaseCallback firebaseCallback) {
        deleteImage(product.getImgsp(), new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if ((boolean) obj == true) {
                    Query query = myRef
                            .child("products")
                            .orderByChild("idsp")
                            .equalTo(product.getIdsp());
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
                } else {
                    firebaseCallback.onCallBack(false);
                }
            }
        });

    }

    public void update(Product oldProduct, Product newProduct, FirebaseCallback firebaseCallback) {
        myRef.child("products").child(oldProduct.getIdsp()).setValue(newProduct);
        myRef.child("products").child(oldProduct.getIdsp()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);
                if (product != null) {
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

    public void deleteImage(String img, FirebaseCallback firebaseCallback) {
        StorageReference desertRef = storageRef.child(img);

        // Delete the file
        desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                firebaseCallback.onCallBack(true);
                // File deleted successfully
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                firebaseCallback.onCallBack(false);
                // Uh-oh, an error occurred!
            }
        });
    }

    public void getAllProductUserID(String id,FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("products")
                .orderByChild("iduser")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Product> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Product product = data.getValue(Product.class);
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

    public void getProductByIdCatChild(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("products")
                .orderByChild("idcatchild")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Product> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Product product = data.getValue(Product.class);
                        if (product.getStatus() == 1) {
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
    public void searchProduct( String queryText,FirebaseCallback firebaseCallback) {
        Query query = myRef
                .child("products")
                .orderByChild("tensp")
                .startAt("%" + queryText)
                .endAt(queryText + "%");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Product> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Product product = data.getValue(Product.class);
                        if (product != null) {
                            categoryArrayList.add(product);
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

    public void setStatusProduct(String id, int status,FirebaseCallback firebaseCallback){
        myRef.child("products").child(id).child("status").setValue(status);
        myRef.child("products").child(id).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long statusnew = (long) snapshot.getValue();
                if(statusnew == status){
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
