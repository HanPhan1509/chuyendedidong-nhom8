package com.example.greentreeonline.Admin.New;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Admin.sanpham;
import com.example.greentreeonline.Admin.themsanpham;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Class.New.Staff;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
import com.example.greentreeonline.Firebase.StaffFirebase;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class nhanvien extends AppCompatActivity {
    RecyclerView recyclerView, resale;
    Toolbar tbsanpham;
    ImageButton add;
    String idUser;

    adapnhanvien adapnhanvien;
    ArrayList<Staff> staffs;

    StaffFirebase staffFirebase;

    private SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        tbsanpham = findViewById(R.id.tbsanpham);

        staffFirebase = new StaffFirebase();

        tbsanpham.setNavigationIcon(R.drawable.back2);
        tbsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nhanvien.this, Navigation.class);
                startActivity(intent);
            }

        });
        recyclerView = findViewById(R.id.listview);
        add = findViewById(R.id.themsp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nhanvien.this, themnhanvien.class);
                startActivity(intent);
            }
        });
        staffs = new ArrayList<>();
        adapnhanvien = new adapnhanvien(nhanvien.this, staffs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapnhanvien);
        getcay();
    }

    public void xoasanpham(int pos) {
        staffFirebase.removeStaff(staffs.get(pos).getIdnv(), new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    staffs.remove(pos);
                    adapnhanvien.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Xóa nhân viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getcay() {
        staffFirebase.getStaffByIdOwner(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(Staff staff: (ArrayList<Staff>) obj){
                    staffs.add(staff);
                }
                adapnhanvien.notifyDataSetChanged();
            }
        });
    }
}
