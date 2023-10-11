package com.poly.duantotnghiep_jf.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;

import java.util.Arrays;

public class TaikhoanJob extends AppCompatActivity {

    TextView btnDangKy,btnLoginBasic, btnwitGG, btnwithFace;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private CallbackManager manager;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan_job);

        mAuth = FirebaseAuth.getInstance();

        anhXa();

        btnLoginBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaikhoanJob.this, DangNhap.class);
                startActivity(intent);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaikhoanJob.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(TaikhoanJob.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
    private void anhXa(){
        btnDangKy = findViewById(R.id.btn_dangky);
        btnLoginBasic = findViewById(R.id.btn_login_basic);
        btnwitGG = findViewById(R.id.btn_login_withGG);
        btnwithFace = findViewById(R.id.btn_login_withFB);
    }

}