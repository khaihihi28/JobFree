package com.poly.duantotnghiep_jf.Adapter;

<<<<<<< HEAD
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
=======
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
>>>>>>> origin/dev
    }

    @Override
    public int getCount() {
<<<<<<< HEAD
        return 4;
    }
}
=======
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
>>>>>>> origin/dev
