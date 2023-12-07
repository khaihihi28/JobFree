package com.poly.duantotnghiep_jf.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.poly.duantotnghiep_jf.R;
///////////////////////////////
public class QuenMatKhau extends AppCompatActivity {


     TextInputEditText edtEmailForgotPass;
     TextView sendForgotPass,textView2;

     ImageView btnBack;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        edtEmailForgotPass = (TextInputEditText) findViewById(R.id.edt_email_forgot_pass);
        sendForgotPass = (TextView) findViewById(R.id.send_forgot_pass);
             textView2 = findViewById(R.id.textView2);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        sendForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Onclickforgotpassword();
            }
        });

        //////////thinhnhph23860
    }

    private  void  Onclickforgotpassword(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = edtEmailForgotPass.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuenMatKhau.this, "Email sent.", Toast.LENGTH_SHORT).show();
                            textView2.setText("Đã Xác Thực Mật Khẩu.Vui Lòng Khởi Động Lại App Để Sử Dụng Dịch Vụ.");
                        }
                    }
                });
    }
}