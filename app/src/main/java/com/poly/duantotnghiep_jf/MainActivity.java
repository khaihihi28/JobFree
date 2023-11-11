package com.poly.duantotnghiep_jf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.poly.duantotnghiep_jf.Activity.TaikhoanJob;
import com.poly.duantotnghiep_jf.Fragment.HomeFragment;
import com.poly.duantotnghiep_jf.Fragment.KhamPhaFragment;
import com.poly.duantotnghiep_jf.Fragment.MenuFragment;
import com.poly.duantotnghiep_jf.Fragment.YeuThichFragment;

public class MainActivity extends AppCompatActivity {
    Button btndangxuat;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        setupBottomNavigationView();
        showInitialFragment();
    }

    private void setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
    private void showInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }
    private void anhXa(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.bnav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.bnav_yeuThich:
                        selectedFragment = new YeuThichFragment();
                        break;
                    case R.id.bnav_khamPha:
                        selectedFragment = new KhamPhaFragment();
                        break;
                    case R.id.bnav_menu:
                        selectedFragment = new MenuFragment();
                        break;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                }

                return true;
            };
}