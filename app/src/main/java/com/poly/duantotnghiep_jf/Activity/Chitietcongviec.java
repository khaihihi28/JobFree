package com.poly.duantotnghiep_jf.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.duantotnghiep_jf.R;

public class Chitietcongviec extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietcongviec);
        int jobId = getIntent().getIntExtra("JOB_ID", -1);
        if (jobId != -1) {
            // Lấy thông tin công việc từ cơ sở dữ liệu hoặc API dựa trên jobId
            // Cập nhật giao diện với thông tin công việc
        }
    }
}