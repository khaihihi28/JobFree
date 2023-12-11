package com.poly.duantotnghiep_jf.thinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;

import java.util.HashMap;
import java.util.Map;

public class Infor extends AppCompatActivity {
    AutoCompleteTextView actv_chuyeNganh, actv_kinhNghiem, actv_trinhDo, actv_timeWork, actv_luong;
    TextView tv_btn_nextThuThap;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_thap_thong_tin);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Account");

        thamChieu();

        ArrayAdapter<CharSequence> adapterChuyenNganh = ArrayAdapter.createFromResource(this, R.array.chuyenNganh, android.R.layout.simple_dropdown_item_1line);
        actv_chuyeNganh.setAdapter(adapterChuyenNganh);
        ArrayAdapter<CharSequence> adapterKinhNghiem = ArrayAdapter.createFromResource(this, R.array.kinhNghiem, android.R.layout.simple_dropdown_item_1line);
        actv_kinhNghiem.setAdapter(adapterKinhNghiem);
        ArrayAdapter<CharSequence> adapterTrinhDo = ArrayAdapter.createFromResource(this, R.array.trinhDo, android.R.layout.simple_dropdown_item_1line);
        actv_trinhDo.setAdapter(adapterTrinhDo);
        ArrayAdapter<CharSequence> adapterTimeWork = ArrayAdapter.createFromResource(this, R.array.timeWork, android.R.layout.simple_dropdown_item_1line);
        actv_timeWork.setAdapter(adapterTimeWork);
        ArrayAdapter<CharSequence> adapterLuong = ArrayAdapter.createFromResource(this, R.array.luong, android.R.layout.simple_dropdown_item_1line);
        actv_luong.setAdapter(adapterLuong);

        tv_btn_nextThuThap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luuThongTinVaoDatabase();
            }
        });
    }

    private void luuThongTinVaoDatabase() {
        // Lấy UID của người dùng hiện tại
        String uid = mAuth.getCurrentUser().getUid();

        // Lấy dữ liệu từ AutoCompleteTextView
        String chuyenNganh = actv_chuyeNganh.getText().toString();
        String kinhNghiem = actv_kinhNghiem.getText().toString();
        String trinhDo = actv_trinhDo.getText().toString();
        String timeWork = actv_timeWork.getText().toString();
        String luong = actv_luong.getText().toString();

        // Kiểm tra xem có dữ liệu nào bị bỏ trống không
        if (chuyenNganh.isEmpty() || timeWork.isEmpty() || luong.isEmpty()) {
            // Hiển thị thông báo lỗi
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo một Map để đưa dữ liệu lên Firebase
        Map<String, Object> accoutnMap = new HashMap<>();
        accoutnMap.put("chuyenNganh", chuyenNganh);
        accoutnMap.put("kinhNghiem", kinhNghiem);
        accoutnMap.put("trinhDo", trinhDo);
        accoutnMap.put("timeWork", timeWork);
        accoutnMap.put("luong", luong);


        // Lưu dữ liệu vào Realtime Database
        mDatabase.child(uid).updateChildren(accoutnMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Nếu lưu dữ liệu thành công, cập nhật giá trị isNewAccount thành false
                mDatabase.child(uid).child("newAccount").setValue(false);

                // Hiển thị thông báo thành công
                Toast.makeText(this, "Chúc bạn có trải nghiệm thật tốt!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Infor.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Hiển thị thông báo lỗi nếu có vấn đề trong quá trình lưu dữ liệu
                Toast.makeText(this, "Có lỗi xảy ra khi lưu dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void thamChieu(){
        actv_chuyeNganh = findViewById(R.id.actv_chuyeNganh);
        actv_kinhNghiem = findViewById(R.id.actv_kinhNghiem);
        actv_trinhDo = findViewById(R.id.actv_trinhDo);
        actv_timeWork = findViewById(R.id.actv_timeWork);
        actv_luong = findViewById(R.id.actv_luong);
        tv_btn_nextThuThap = findViewById(R.id.tv_btn_nextThuThap);
    }
}