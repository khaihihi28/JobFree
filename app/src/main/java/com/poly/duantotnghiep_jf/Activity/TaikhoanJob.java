package com.poly.duantotnghiep_jf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.Model.Account;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.Util.Alert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TaikhoanJob extends AppCompatActivity {

    TextView btnDangKy, btnLoginBasic, btnwithGG, btnwithFace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan_job);
        anhXa();
        login();
        loginWithGoogle();
        loginWithFaceBook();
        register();
    }


    private void loginWithFaceBook() {
        btnwithFace.setOnClickListener(v -> {
            startSignInFB();
        });
    }

    private void loginWithGoogle() {
        btnwithGG.setOnClickListener(v -> {
            startSignInGG();
        });
    }

    private void startSignInGG() {
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.GoogleBuilder()
                        .build()
        );
        AuthUI authUI = AuthUI.getInstance();
        Intent signInIntent = authUI
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false, true)
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                        "https://superapp.example.com/terms-of-service.html",
                        "https://superapp.example.com/privacy-policy.html"
                )
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void startSignInFB() {
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.FacebookBuilder().build()
        );
        AuthUI authUI = AuthUI.getInstance();
        Intent signInIntent = authUI
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false, true)
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                        "https://superapp.example.com/terms-of-service.html",
                        "https://superapp.example.com/privacy-policy.html"
                )
                .build();
        signInLauncher.launch(signInIntent);
    }


    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            this::onSignInResult
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                Alert.showSnackbarMessage(btnwithGG, "Đăng nhập thất bại 1");
                return;
            }
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Account").child(user.getUid());
            myRef.get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    Alert.showSnackbarMessage(btnwithGG, "Kiểm tra kết nối mạng");
                    return;
                }
                if (task.getResult().getValue() == null) {
                    Account account = new Account(Objects.requireNonNull(user.getPhotoUrl()).toString(), user.getEmail(), user.getDisplayName(), user.getPhoneNumber(), true);
                    myRef.setValue(account);
                    Intent intent = new Intent(TaikhoanJob.this, ThuThapThongTin.class);
                    startActivity(intent);


                } else {
                    if (Objects.requireNonNull(task.getResult().getValue(Account.class)).isNewAccount()) {
                        Intent intent = new Intent(TaikhoanJob.this, ThuThapThongTin.class);
                        startActivity(intent);
                        return;
                    }
                    Alert.showSnackbarMessage(btnwithGG, "Đăng nhập thành công");
                    Intent intent = new Intent(TaikhoanJob.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            });
        } else {
            Alert.showSnackbarMessage(btnwithGG, "Đăng nhập thất bại 0");
        }
    }


    private void login() {
        btnLoginBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaikhoanJob.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaikhoanJob.this, DangKy.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        btnDangKy = findViewById(R.id.btn_dangky);
        btnLoginBasic = findViewById(R.id.btn_login_basic);
        btnwithGG = findViewById(R.id.btn_login_withGG);
        btnwithFace = findViewById(R.id.btn_login_withFB);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), GoogleSignInOptions.DEFAULT_SIGN_IN);
        googleSignInClient.signOut();
    }
}