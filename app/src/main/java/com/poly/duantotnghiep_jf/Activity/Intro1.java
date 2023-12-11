package com.poly.duantotnghiep_jf.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.duantotnghiep_jf.Helper.AuthHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;

public class Intro1 extends AppCompatActivity {
    private ImageView firstImageView;
    private ImageView secondImageView;

    private static final long SPLASH_DELAY = 4000; // 4 giây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        firstImageView = findViewById(R.id.firstImageView);
        secondImageView = findViewById(R.id.secondImageView);

        // Tạo animation cho sự di chuyển từ trên xuống
        ValueAnimator slideDownAnimator = ValueAnimator.ofFloat(-1000, 0);
        slideDownAnimator.setDuration(2000);
        slideDownAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                firstImageView.setTranslationY(value);
            }
        });
        slideDownAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Khi sự di chuyển đã kết thúc, hiển thị hình ảnh thứ hai
                ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(secondImageView, "alpha", 0, 1);
                fadeInAnimator.setDuration(500);
                secondImageView.setVisibility(View.VISIBLE);
                fadeInAnimator.start();
            }
        });

        slideDownAnimator.start();
        if(FireBaseHelper.isUserLoggedIn()){
            FireBaseHelper.checkIsNewAccount(isNewAccount -> {
                if(isNewAccount){
                    AuthHelper.signOutHelper();
                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(FireBaseHelper.isUserLoggedIn()){
                    intent = new Intent(Intro1.this, MainActivity.class);
                }
                else{
                    intent = new Intent(Intro1.this, TaikhoanJob.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}