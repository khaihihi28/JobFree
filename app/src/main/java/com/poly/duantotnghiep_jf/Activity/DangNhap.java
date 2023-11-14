package com.poly.duantotnghiep_jf.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Helper.AuthHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;

public class DangNhap extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference mData;
    TextInputEditText edtEmail, edtPass;
    Button btnLogin;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        anhXa();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, TaikhoanJob.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });
    }

    private void dangNhap(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        AuthHelper.signInHelper(email, password, new AuthHelper.OnLoginCompleteListener() {
            @Override
            public void onLoginSuccess(String uid) {
                FireBaseHelper.checkIsNewAccount(isNewAccount -> {
                    if(isNewAccount){
                        // 'isNewAccount' là true, chuyển người dùng đến màn hình điền thông tin
                        Intent intent = new Intent(DangNhap.this, ThuThapThongTin.class);
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else {
//                      'isNewAccount' là false, chuyển người dùng đến màn hình chính (trang chủ)
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                Toast.makeText(DangNhap.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(DangNhap.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }

    private void anhXa(){
        btnBack = findViewById(R.id.btnBack);
        edtEmail = findViewById(R.id.edtEmail_Login);
        edtPass = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
    }
}