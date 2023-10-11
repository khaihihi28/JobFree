package com.poly.duantotnghiep_jf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.poly.duantotnghiep_jf.R;

public class ManHinhTest extends AppCompatActivity {

TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_test);

        tvTest= findViewById(R.id.tvTest);


    }
}