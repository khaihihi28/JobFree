package com.poly.duantotnghiep_jf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.poly.duantotnghiep_jf.R;

public class DangNhap extends AppCompatActivity {
    ImageView btnBack;
    EditText edtEmail_Login,edt_password_login ;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btnBack = findViewById(R.id.btnBack);

        edtEmail_Login = findViewById(R.id.edtEmail_Login);
        edt_password_login = findViewById(R.id.edt_password_login);
        btn_login = findViewById(R.id.btn_login);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, TaikhoanJob.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =  edtEmail_Login.getText().toString();
                String pass = edt_password_login.getText().toString();
            }
        });
    }
}