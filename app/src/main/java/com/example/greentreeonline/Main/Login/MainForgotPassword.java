package com.example.greentreeonline.Main.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greentreeonline.Class.Profile;
import com.example.greentreeonline.Class.User.User;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.UserFireBase;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import org.simple.eventbus.EventBus;

public class MainForgotPassword extends AppCompatActivity {
    public static final String TAG = MainLogin.class.getSimpleName();
    Profile acc;
    private EditText edtEmail;
    private EditText edtmk;
    private EditText edtconmk;
    private TextView btnLogin;
    private ProgressDialog pDialog;
    Toolbar toolbar;
    String tentaikhoan;
    String matkhau;
    ProgressBar loading;
    public static final String login = ConnectServer.login;

    public static final String KEY_USERNAME = "taikhoan";
    public static final String KEY_PASSWORD = "matkhau";

    private SharedPreferences sharedPreferences;
    private UserFireBase userFireBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quenmatkhau);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        userFireBase = new UserFireBase();

        addControl();
        btdangnhap();
    }

    private void addControl() {
        edtEmail = (EditText) findViewById(R.id.txttaikhoan);
        edtmk = (EditText) findViewById(R.id.txtmatkhau);
        edtconmk = (EditText) findViewById(R.id.txtconfirmmatkhau);
        btnLogin = findViewById(R.id.btn_dangnhap);
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
        if (edtEmail.getText().toString().equals("")) {
            edtEmail.setError("Vui lòng nhập tên tài khoản");
        } else if (edtmk.getText().toString().equals("")) {
            edtmk.setError("Vui lòng nhập mật khẩu");
        } else if(!edtmk.getText().toString().equals(edtconmk.getText().toString())){
            edtconmk.setError("Vui lòng nhập đúng mật khẩu");
        }
        else {
            tentaikhoan = edtEmail.getText().toString().trim();
            matkhau = edtmk.getText().toString().trim();
            progressDialog.setMessage("Đang đổi mật khẩu...");
            progressDialog.show();
            userFireBase.checkExistGmail(tentaikhoan, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if ((boolean)obj == true) {
                        userFireBase.resetPassword(tentaikhoan, matkhau, new FirebaseCallback() {
                            @Override
                            public void onCallBack(Object obj) {
                                if((boolean) obj == true){
                                    startActivity(new Intent(getApplicationContext(), MainLogin.class));
                                    progressDialog.dismiss();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(MainForgotPassword.this, "Có lỗi xảy ra vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainForgotPassword.this, "Sai email!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }
}
