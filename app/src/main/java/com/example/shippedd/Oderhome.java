package com.example.shippedd;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Oderhome extends AppCompatActivity {
    private TabLayout mtablayout;
    private ViewPager2 mviewpager;
    private Mypager mypager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.donhang);


        mtablayout = findViewById(R.id.tablayout);
        mviewpager = findViewById(R.id.viewpage1r);
        mypager = new Mypager(this);
        mviewpager.setAdapter(mypager);
        new TabLayoutMediator(mtablayout, mviewpager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Shop");
                    break;
                case 1:
                    tab.setText("Chờ xác nhân");
                    break;
                case 2:
                    tab.setText("Đang giao");
                    break;
                case 3:
                    tab.setText("Đã nhận");
                    break;
                case 4:
                    tab.setText("Đã hủy");
                    break;

            }
        }).attach();
    }
}
