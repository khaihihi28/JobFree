package com.poly.duantotnghiep_jf.adapter1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sanphamdemo.fragment.KhachDatLichFragment;
import com.example.sanphamdemo.fragment.TatCaFragment;

public class Adapter_of_ViewPage extends FragmentStateAdapter{



    public Adapter_of_ViewPage(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = TatCaFragment.newInstance();
                 break;
            case 1:
                fragment = KhachDatLichFragment.newInstance();
                break;

        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2; // số trang
    }
}
