package com.example.greentreeonline.Admin.New;

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

public class updatenhanvien extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    EditText ten, email, sdt, dc;
    Button addkh;
    boolean openimage = true;
    Staff staff;

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
        addkh.setText("Update nhân viên");
        addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStaff();
                finish();
            }
        });
        getDatachitiet();
    }

    public void updateStaff(){
        tennv = ten.getText().toString().trim();
        emailnv = email.getText().toString().trim();
        dcmv = dc.getText().toString().trim();
        sdtnv = sdt.getText().toString().trim();

        if(tennv.isEmpty() || emailnv.isEmpty() || dcmv.isEmpty() || sdtnv.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ các thông tin", Toast.LENGTH_SHORT).show();
        }else{
            openimage = false;
            Staff staff1 = new Staff(staff.getIdnv(), tennv, emailnv, sdtnv, dcmv, idUser);
            staffFirebase.updateStaff(staff1, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if((boolean) obj == true){
                        Toast.makeText(getApplicationContext(), "Cập nhật nhân viên thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void getDatachitiet(){
        Intent intent = getIntent();
        staff = (Staff) intent.getSerializableExtra("update");
        ten.setText(staff.getTennv());
        email.setText(staff.getEmailnv());
        sdt.setText(staff.getSdtnv());
        dc.setText(staff.getDcnv());
    }


    @Override
    protected void onStop() {
        if(!openimage){
            startActivity(new Intent(this, nhanvien.class));
        }
        super.onStop();
    }
}
