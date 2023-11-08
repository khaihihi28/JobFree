package com.poly.duantotnghiep_jf.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.test_chuc_nang_yeu_thich.List_test;

public class DangNhap extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference mData;
    TextInputEditText edtEmail, edtPass;
    Button btnLogin, btnlist;
    ImageView btnBack;

    TextView forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        anhXa();

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, QuenMatKhau.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, TaikhoanJob.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });
        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, List_test.class));

            }
        });
    }

    private void dangNhap(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();
                            DatabaseReference isNewAccountReference = mData.child("Account").child(uid).child("newAccount");
                            isNewAccountReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists() && snapshot.getValue() instanceof Boolean) {
                                        boolean isNewAccount = (boolean) snapshot.getValue();

                                        if (isNewAccount) {
                                            // 'isNewAccount' là true, chuyển người dùng đến màn hình điền thông tin
                                            Intent intent = new Intent(DangNhap.this, QuenMatKhau.class);
                                            Toast.makeText(DangNhap.this, uid, Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // 'isNewAccount' là false, chuyển người dùng đến màn hình chính (trang chủ)
                                            Intent intent = new Intent(DangNhap.this, MainActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(DangNhap.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        // Dữ liệu không tồn tại hoặc không phải kiểu dữ liệu boolean
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Toast.makeText(DangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DangNhap.this, "Fail!!", Toast.LENGTH_SHORT).show();
                        }
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
        btnlist = findViewById(R.id.btnlist) ;
        forgotpassword = findViewById(R.id.tv_quen_mk);
        btnBack = findViewById(R.id.btnBack);
        edtEmail = findViewById(R.id.edtEmail_Login);
        edtPass = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
    }
}