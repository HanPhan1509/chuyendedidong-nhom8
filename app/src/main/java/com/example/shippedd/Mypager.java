package com.example.shippedd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Mypager extends FragmentStateAdapter {


    public Mypager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new frag_xacnhan();
            case 1:
                return new frag_danggiao();
            case 2:
                return new frag_danhan();
            case 3:
                return new frag_trahang();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
