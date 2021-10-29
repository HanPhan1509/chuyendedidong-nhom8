package com.example.greentreeonline.Main.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greentreeonline.Class.User.User;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.UserFireBase;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import java.util.HashMap;
import java.util.Map;

public class MainUpdateProfile extends AppCompatActivity {
    String gioitinh, idUser;
    EditText edtmk, edthoten, edtsđt, edtdiachi, edtemail, edgioitinh;
    SharedPreferences luutaikhoan;
    private SharedPreferences.Editor editor;
    Button btnluu, btnhuy;

    UserFireBase userFireBase;

    //  List<objacc> acc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatetaikhoan);
        userFireBase = new UserFireBase();
        luutaikhoan = getSharedPreferences("luutaikhoan", Context.MODE_PRIVATE);
        editor = luutaikhoan.edit();

        AnhXa();
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suathongtin();
            }
        });


    }

    private void Suathongtin() {
        final String hoten = edthoten.getText().toString().trim();
        final String sđt = edtsđt.getText().toString().trim();
        final String diachi = edtdiachi.getText().toString().trim();
        final String email = edtemail.getText().toString().trim();
        final String matkhau = edtmk.getText().toString().trim();
        final String gt = edgioitinh.getText().toString().trim();


        User user = new User(idUser, hoten, sđt, email, diachi, gt, "", matkhau, "");
        userFireBase.update(idUser, user, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                if((boolean) obj == true){
                    editor.putString("hoten", hoten);
                    editor.putString("gioitinh", gt);
                    editor.putString("sdt", sđt);
                    editor.putString("diachi", diachi);
                    editor.putString("gmail", email);
                    editor.commit();
                    Toast.makeText(MainUpdateProfile.this, "Cập nhật tài khoản thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainUpdateProfile.this, Navigation.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainUpdateProfile.this, "Lỗi !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void AnhXa() {
        btnluu = (Button) findViewById(R.id.btnluuthongtin);

        edtmk = (EditText) findViewById(R.id.edmatkhau);
        edthoten = (EditText) findViewById(R.id.edhoten);
        edtsđt = (EditText) findViewById(R.id.edsdt);
        edtdiachi = (EditText) findViewById(R.id.eddiachi);
        edtemail = (EditText) findViewById(R.id.edemail);
        edgioitinh = findViewById(R.id.edgioitinh);

        //String TenTk=luutaikhoan.getString("tk","");
        edthoten.setText(luutaikhoan.getString("hoten", ""));
        edgioitinh.setText(luutaikhoan.getString("gioitinh", ""));
        edtsđt.setText(luutaikhoan.getString("sdt", ""));
        edtdiachi.setText(luutaikhoan.getString("diachi", ""));
        edtemail.setText(luutaikhoan.getString("gmail", ""));
        edtmk.setText(luutaikhoan.getString("matkhau", ""));
        idUser = luutaikhoan.getString("id", "");

    }
}

