package com.example.greentreeonline.Main.Login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Class.User.User;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.UserFireBase;
import com.example.greentreeonline.R;

import java.util.HashMap;
import java.util.Map;

public class MainRegister extends AppCompatActivity {
    EditText TK, MK, sdt, diachi, hoten, email, ngay, gt;
    RadioButton radioButton_nam, radioButton_nu;
    TextView bt_register;
    String str_id, str_tk, str_mk, str_hoten, str_diachi, str_sdt, str_gioitinh, str_email, str_ngay, str_gt;
    UserFireBase userFireBase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_dangky);
        userFireBase = new UserFireBase();
        MK = (EditText) findViewById(R.id.password);
        sdt = findViewById(R.id.phone);
        diachi = findViewById(R.id.diachi);
        hoten = findViewById(R.id.fullName);
        ngay = findViewById(R.id.date);
        gt=findViewById(R.id.gt);
        email = findViewById(R.id.Email);
        bt_register = findViewById(R.id.registerBtn);
//        radioButton_nam.setOnCheckedChangeListener(listenerRadio);
//        radioButton_nu.setOnCheckedChangeListener(listenerRadio);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    public void Register() {
        if (email.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        } else if (MK.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {

            str_hoten = hoten.getText().toString().trim();
            str_ngay = ngay.getText().toString().trim();
            str_email = email.getText().toString().trim();
            str_diachi = diachi.getText().toString().trim();
            str_sdt = sdt.getText().toString().trim();
            str_gioitinh= gt.getText().toString().trim();
            str_diachi = diachi.getText().toString().trim();
            str_mk = MK.getText().toString().trim();
            userFireBase.checkExistSDT(str_sdt, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if((boolean) obj == false) {
                        userFireBase.checkExistGmail(str_email, new FirebaseCallback() {
                            @Override
                            public void onCallBack(Object obj) {
                                if ((boolean) obj == false) {
                                    User user = new User("",str_hoten, str_sdt, str_email, str_diachi, str_gioitinh, str_ngay, str_mk, "");
                                    userFireBase.addUser(user, new FirebaseCallback() {
                                        @Override
                                        public void onCallBack(Object obj) {
                                            if ((boolean) obj == true){
                                                Toast.makeText(MainRegister.this, "ok", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), MainLogin.class));
                                            } else{
                                                Toast.makeText(MainRegister.this, "Đã có lỗi xảy ra vui lòng thử lại !", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else  {
                                    Toast.makeText(MainRegister.this, "Email đã được sử dụng !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(MainRegister.this, "Số điện thoại đã được sử dụng !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}

