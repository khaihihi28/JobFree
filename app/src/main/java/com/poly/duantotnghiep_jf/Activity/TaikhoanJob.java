package com.poly.duantotnghiep_jf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.poly.duantotnghiep_jf.R;

public class TaikhoanJob extends AppCompatActivity {
    TextView btnLoginBasic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan_job);

        btnLoginBasic = findViewById(R.id.btn_login_basic);

        btnLoginBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaikhoanJob.this, DangNhap.class);
                startActivity(intent);
            }
        });


    }
}