package com.poly.duantotnghiep_jf.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.poly.duantotnghiep_jf.Fragment.Discover;
import com.poly.duantotnghiep_jf.Fragment.Home1;
import com.poly.duantotnghiep_jf.Fragment.Like;
import com.poly.duantotnghiep_jf.Fragment.menu;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home1();
            case 1:
                return new Like();
            case 2:
                return new Discover();
            case 3:
                return new menu();
        }
        return new Home1();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
