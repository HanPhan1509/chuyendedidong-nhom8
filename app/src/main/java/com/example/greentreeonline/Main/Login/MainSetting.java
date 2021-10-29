package com.example.greentreeonline.Main.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.greentreeonline.Main.MainMap;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import org.simple.eventbus.EventBus;

public class MainSetting extends AppCompatActivity {
    Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Button dx, dcs, dn, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        EventBus.getDefault().register(this);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        dx = findViewById(R.id.dangxuat);
        dn = findViewById(R.id.dangnhap);
        dcs = findViewById(R.id.dcshop);
        update = findViewById(R.id.updatetk);
        toolbar = findViewById(R.id.tbsettting);
        toolbar.setNavigationIcon(R.drawable.back2);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        dangxuat();
        dangnhap();
        check();
        diachishop();
    }

    public void diachishop() {
        dcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSetting.this, MainMap.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSetting.this, MainUpdateProfile.class);
                startActivity(intent);
            }
        });
    }

    public void check() {
        String TenTk = sharedPreferences.getString("taikhoan", "");
        if (!TextUtils.isEmpty(TenTk)) {
            dx.setVisibility(View.VISIBLE);
            dn.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(MainSetting.this, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
            //  startActivity(new Intent(getActivity(),dangnhap.class));
            dx.setVisibility(View.INVISIBLE);
            dn.setVisibility(View.VISIBLE);

            // finish();

        }
    }

    public void dangnhap() {
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSetting.this, MainLogin.class);
                startActivity(intent);
            }
        });
    }

    public void dangxuat() {

        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.commit();

                EventBus.getDefault().post(true, "loginSuccess");
                Intent intent = new Intent(MainSetting.this, Navigation.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Bạn Đã Đăng Xuất! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
