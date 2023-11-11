package com.poly.duantotnghiep_jf.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.poly.duantotnghiep_jf.Adapter.HomeSliderAdapter;
import com.poly.duantotnghiep_jf.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private HomeSliderAdapter sliderAdapter;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        setupSlider();

        return view;
    }

    private void setupSlider() {
        List<Integer> imageList = Arrays.asList(
                R.drawable.logo,
                R.drawable.logo_don_main,
                R.drawable.logofb,
                R.drawable.logo2,
                R.drawable.chu_logo,
                R.drawable.logo_main
        );

        sliderAdapter = new HomeSliderAdapter(imageList);
        viewPager.setAdapter(sliderAdapter);

        // Auto chuyển đổi slide mỗi 3 giây
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = imageList.size();
                int nextItem = (currentItem + 1) % totalItems;
                viewPager.setCurrentItem(nextItem);
                handler.postDelayed(this, 3000); // 3000 milliseconds = 3 seconds
            }
        };
        // Khởi động auto chuyển đổi
        handler.postDelayed(runnable, 3000); // 3000 milliseconds = 3 seconds
    }
    @Override
    public void onDestroyView() {
        // Dừng auto chuyển đổi khi Fragment bị hủy
        handler.removeCallbacks(runnable);
        super.onDestroyView();
    }
}
