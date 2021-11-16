package com.example.greentreeonline.Admin.New;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greentreeonline.Class.New.Staff;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.StaffFirebase;
import com.example.greentreeonline.R;

public class themnhanvien extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    EditText ten, email, sdt, dc;
    Button addkh;
    boolean openimage = true;

    String idUser;
    //Product
    String tennv, emailnv, dcmv, sdtnv;

    Toolbar toolbar;

    StaffFirebase staffFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themnhanvien);

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        staffFirebase = new StaffFirebase();

        ten = (EditText) findViewById(R.id.tennv);
        email = findViewById(R.id.emailnv);
        dc = findViewById(R.id.dcnv);
        sdt = findViewById(R.id.sdtnv);
        addkh = (Button) findViewById(R.id.addsanpham);
        addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStaff();
                finish();
            }
        });
    }

    public void addStaff(){
        tennv = ten.getText().toString().trim();
        emailnv = email.getText().toString().trim();
        dcmv = dc.getText().toString().trim();
        sdtnv = sdt.getText().toString().trim();

        if(tennv.isEmpty() || emailnv.isEmpty() || dcmv.isEmpty() || sdtnv.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ các thông tin", Toast.LENGTH_SHORT).show();
        }else{
            openimage = false;
            Staff staff = new Staff("", tennv, emailnv, sdtnv, dcmv, idUser);
            staffFirebase.addStaff(staff, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if((boolean) obj == true){
                        Toast.makeText(getApplicationContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    @Override
    protected void onStop() {
        if(!openimage){
            startActivity(new Intent(this, nhanvien.class));
        }
        super.onStop();
    }


}
