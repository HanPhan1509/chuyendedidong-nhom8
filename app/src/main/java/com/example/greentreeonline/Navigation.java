package com.example.greentreeonline;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.greentreeonline.Admin.admin;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Fragment.FractmentCategory;
import com.example.greentreeonline.Fragment.FragmentHome;
import com.example.greentreeonline.Fragment.FragmentIInformation;
import com.example.greentreeonline.Fragment.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.simple.eventbus.EventBus;

public class  Navigation extends AppCompatActivity {
    private String url = ConnectServer.login;
    BottomNavigationView bottomNav;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.navi);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        EventBus.getDefault().register(this);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    new FragmentHome<>()).commit();
            phanhe();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.trangchu:
                            selectedFragment = new FragmentHome();
                            break;
                        case R.id.dao:
                            selectedFragment = new FractmentCategory();
                            break;
                        case R.id.yeuthich:
                            selectedFragment = new FragmentIInformation();
                            break;
                        case R.id.taikhoan:
                            selectedFragment = new FragmentProfile();
                            break;
                        case R.id.thongke:
                            selectedFragment = new admin();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void phanhe() {

        String id = sharedPreferences.getString("id", "0");
        bottomNav.getMenu().findItem(R.id.trangchu).setVisible(true);
        bottomNav.getMenu().findItem(R.id.dao).setVisible(true);
//        bottomNav.getMenu().findItem(R.id.yeuthich).setVisible(true);
        bottomNav.getMenu().findItem(R.id.taikhoan).setVisible(true);
        bottomNav.getMenu().findItem(R.id.thongke).setVisible(true);

//        }else {
//            bottomNav.getMenu().findItem(R.id.trangchu).setVisible(true);
//            bottomNav.getMenu().findItem(R.id.dao).setVisible(true);
//            bottomNav.getMenu().findItem(R.id.yeuthich).setVisible(true);
//            bottomNav.getMenu().findItem(R.id.taikhoan).setVisible(true);
//            bottomNav.getMenu().findItem(R.id.thongke).setVisible(false);
//        }
        EventBus.getDefault().post(true, "loginSuccess");
    }
}


