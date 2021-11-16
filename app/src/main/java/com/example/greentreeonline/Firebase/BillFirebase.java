package com.example.greentreeonline.Firebase;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.greentreeonline.Class.New.Bill;
import com.example.greentreeonline.Class.New.Cart;
import com.example.greentreeonline.Class.New.CartShop;
import com.example.greentreeonline.Class.New.ProductBill;
import com.example.greentreeonline.Main.Oder.MainOrder;
import com.example.greentreeonline.Navigation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Struct;
import java.util.ArrayList;

public class BillFirebase {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    CartFirebase cartFirebase;
    DoanhThuFirebase doanhThuFirebase;

    public BillFirebase(){
        cartFirebase = new CartFirebase();
        doanhThuFirebase = new DoanhThuFirebase();
    }

    public void addBills(ArrayList<CartShop> cartShops, Bill temp, FirebaseCallback firebaseCallback){
        for(CartShop cartShop: cartShops){
            ArrayList<ProductBill> productBillArrayList = new ArrayList<>();
            int gia = 0;
            for(Cart cart: cartShop.getCarts()){
                ProductBill productBill = new ProductBill("",cart.getIdsp(), cart.getTensp(), cart.getImgsp(), cart.getGia(), cart.getSl(), cart.getIdshop(), 0, "Đang chờ lấy hàng");
                productBillArrayList.add(productBill);
                gia += cart.tongTien();
            }
            Bill bill = new Bill("", temp.getIduser(), cartShop.getIdshop(), temp.getTenkh(), temp.getDt(), temp.getDiachi(), temp.getDate(), gia, 30000, gia + 30000, 0, "Đang chờ xác nhận");
            addBill(bill, productBillArrayList, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if((boolean) obj == true){
                        cartFirebase.deleteCartUserId(temp.getIduser(), cartShop.getIdshop(),new FirebaseCallback() {
                            @Override
                            public void onCallBack(Object obj) {

                            }
                        });
                    }
                }
            });
        }
        firebaseCallback.onCallBack(true);
    }

    public void addBill(Bill bill, ArrayList<ProductBill> productBillArrayList, FirebaseCallback firebaseCallback){
        String billID = myRef.child("bill").push().getKey();
        bill.setId(billID);
        myRef.child("bill").child(billID).setValue(bill);
        myRef.child("bill").child(billID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bill favourite1 = dataSnapshot.getValue(Bill.class);
                boolean check;
                if (favourite1 != null) {
                    for(ProductBill productBill: productBillArrayList){
                        addProuctBill(productBill, billID);
                    }
                    doanhThuFirebase.addDoanhThu(bill.getIdshop(), bill.getTamtinh(), new FirebaseCallback() {
                        @Override
                        public void onCallBack(Object obj) {

                        }
                    });
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

    public void addProuctBill(ProductBill productBill, String billId){
        System.out.println(productBill.getIdshop());
        productBill.setIdbill(billId);
        String productBillId = myRef.child("productbill").push().getKey();
        myRef.child("productbill").child(productBillId).setValue(productBill);
        myRef.child("productbill").child(productBillId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getBillByUserId(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("bill")
                .orderByChild("iduser")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Bill> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Bill product = data.getValue(Bill.class);
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

    public void getBillByShopId(String id, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("bill")
                .orderByChild("idshop")
                .equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Bill> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Bill product = data.getValue(Bill.class);
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


    public void getProductBillByBillId(String billId, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("productbill")
                .orderByChild("idbill")
                .equalTo(billId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ProductBill> categoryArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        ProductBill product = data.getValue(ProductBill.class);
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
    public void updateBillStatus (String billId, int status, String namestatus,FirebaseCallback firebaseCallback){
        myRef.child("bill").child(billId).child("namestatus").setValue(namestatus);
        myRef.child("bill").child(billId).child("status").setValue(status);
        myRef.child("bill").child(billId).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void removeBill(String billID, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("bill")
                .orderByChild("id")
                .equalTo(billID);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        data.getRef().removeValue();
                    }
                    removeProductBill(billID, firebaseCallback);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseCallback.onCallBack(false);
            }
        });
    }

    public void removeProductBill(String idbill, FirebaseCallback firebaseCallback){
        Query query = myRef
                .child("productbill")
                .orderByChild("idbill")
                .equalTo(idbill);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        data.getRef().removeValue();
                    }
                    firebaseCallback.onCallBack(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseCallback.onCallBack(false);
            }
        });
    }
}
