package com.poly.duantotnghiep_jf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.poly.duantotnghiep_jf.R;

public class TaikhoanJob extends AppCompatActivity {

    TextView btnLoginBasic, btnwitGG, btnwithFace;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //private CallbackManager manager;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan_job);

        btnLoginBasic = findViewById(R.id.btn_login_basic);
        btnwitGG = findViewById(R.id.btn_login_withGG);
        btnwithFace = findViewById(R.id.btn_login_withFB);
        btnLoginBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaikhoanJob.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

}