package com.poly.duantotnghiep_jf.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.Helper.AuthHelper;
import com.poly.duantotnghiep_jf.Model.Account;
import com.poly.duantotnghiep_jf.R;

public class DangKy extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    TextInputEditText edtName, edtHo, edtPhone, edtEmail, edtPass;
    TextView btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Account");
        anhXa();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKy();
            }
        });
    }



    private void dangKy(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        String name = edtName.getText().toString();
        String ho = edtHo.getText().toString();
        String phone = edtPhone.getText().toString();
        AuthHelper.signUpHelper(email, password, name, ho, phone, new AuthHelper.OnRegistrationCompleteListener() {
            @Override
            public void onRegistrationSuccess(String uid) {
                Toast.makeText(DangKy.this, "Đăng ký thành công!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangKy.this, TaikhoanJob.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onRegistrationFailure(String errorMessage) {
                Toast.makeText(DangKy.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhXa(){
        edtName = (TextInputEditText) findViewById(R.id.edt_Name_Dky);
        edtHo = (TextInputEditText) findViewById(R.id.edt_Ho_Dky);
        edtPhone = (TextInputEditText) findViewById(R.id.edt_phone_Dky);
        edtEmail = (TextInputEditText) findViewById(R.id.edt_Emai_Dky);
        edtPass = (TextInputEditText) findViewById(R.id.edt_Pass_Dky);
        btnDangKy = (TextView) findViewById(R.id.tv_Btn_DangKy);
    }
}