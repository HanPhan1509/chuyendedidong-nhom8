package com.example.greentreeonline.Main.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Class.Profile;
import com.example.greentreeonline.Class.User.User;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.UserFireBase;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class MainLogin extends AppCompatActivity {

    public static final String TAG = MainLogin.class.getSimpleName();
    Profile acc;
    private EditText edttk;
    private EditText edtmk;
    private EditText edtEmail;
    private TextView dangky;
    private TextView btnLogin;
    private TextView quenmatkhau;
    private ProgressDialog pDialog;
    Toolbar toolbar;
    String tentaikhoan;
    String matkhau;
    ProgressBar loading;
    public static final String login = ConnectServer.login;

    public static final String KEY_USERNAME = "taikhoan";
    public static final String KEY_PASSWORD = "matkhau";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private UserFireBase userFireBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dangnhap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        userFireBase = new UserFireBase();
        EventBus.getDefault().register(this);


        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        addControl();
        btdangnhap();

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainRegister.class);
                startActivity(intent);
            }
        });
        quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLogin.this, MainForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        edttk = (EditText) findViewById(R.id.txttaikhoan);
        edtmk = (EditText) findViewById(R.id.txtmatkhau);
        btnLogin = findViewById(R.id.btn_dangnhap);
        quenmatkhau = findViewById(R.id.forgotPassword);
        dangky = findViewById(R.id.TxtRegister);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    public void btdangnhap() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login(view);


            }
        });

    }

    public void Login(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        if (edttk.getText().toString().equals("")) {
            edttk.setError("Vui lòng nhập tên tài khoản");
        } else if (edtmk.getText().toString().equals("")) {
            edtmk.setError("Vui lòng nhập mật khẩu");
        } else {
            tentaikhoan = edttk.getText().toString().trim();
            matkhau = edtmk.getText().toString().trim();
            progressDialog.setMessage("Đang đăng nhập...");
            progressDialog.show();
            userFireBase.login(tentaikhoan, matkhau, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if (obj != null) {
                        User user = new User((User) obj);
                        //thongtin.acc.add(new objacc(id, TenTK, Hoten, gioitinh, sdt, gmail, date, diachi, img));
                        editor.putString("id", user.getId());
                        editor.putString("taikhoan", user.getGmail());
                        editor.putString("hoten", user.getHoten());
                        editor.putString("gioitinh", user.getGioitinh());
                        editor.putString("sdt", user.getSdt());
                        editor.putString("gmail", user.getGmail());
                        editor.putString("date", user.getNgaysinh());
                        editor.putString("diachi", user.getDiachi());
                        editor.putString("imgtk", user.getImgtk());
                        editor.commit();

                        EventBus.getDefault().post(true, "loginSuccess");
                        startActivity(new Intent(getApplicationContext(), Navigation.class));
                        // finish();

                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainLogin.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }


}


