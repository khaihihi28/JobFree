package com.poly.duantotnghiep_jf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;

public class ManHinhChao extends AppCompatActivity {
    private ImageView firstImageView;
    private ImageView secondImageView;

    private FirebaseAuth mAuth;

    private static final long SPLASH_DELAY = 4000; // 4 giây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        mAuth = FirebaseAuth.getInstance();

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Intent intent;
                if(currentUser != null){
                    intent = new Intent(ManHinhChao.this, MainActivity.class);
                }
                else{
                    intent = new Intent(ManHinhChao.this, TaikhoanJob.class);
                }
                startActivity(intent);
            }
        }, SPLASH_DELAY);
    }
    ////////////////////////////thinhnhph23860

}