package com.poly.duantotnghiep_jf.test_chuc_nang_yeu_thich;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.Activity.DangKy;
import com.poly.duantotnghiep_jf.Activity.TaikhoanJob;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.Model.Account;
import com.poly.duantotnghiep_jf.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterRec extends     RecyclerView.Adapter<AdapterRec.UserViewholer> {
    private List<User_test>list;

    public AdapterRec(List<User_test> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test,parent,false);

        return new UserViewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewholer holder, int position) {
      User_test userTest  = list.get(position);
      if(userTest ==null){
          return;
      }
      holder.email.setText(userTest.getEmail());


      holder.button4.setText("like");

     holder.button4.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

if(holder.button4.isChecked()){
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("MyList");
//    FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    String uid = mAuth.getCurrentUser().getUid();
    String emaill = userTest.getEmail();
    Map<String, Object> accoutnMap = new HashMap<>();
    accoutnMap.put("email", emaill);
    // Lưu dữ liệu vào Realtime Database
//    myRef.child(uid).updateChildren(accoutnMap).addOnCompleteListener(task -> {
//        if (task.isSuccessful()) {
//            // Nếu lưu dữ liệu thành công, cập nhật giá trị isNewAccount thành false
//           // mDatabase.child(uid).child("newAccount").setValue(false);
//
//            // Hiển thị thông báo thành công
//            Toast.makeText(v.getContext(), "Chúc bạn có trải nghiệm thật tốt!!!", Toast.LENGTH_SHORT).show();
////            Intent intent = new Intent(ThuThapThongTin.this, MainActivity.class);
////            startActivity(intent);
////            finish();
//        } else {
//            // Hiển thị thông báo lỗi nếu có vấn đề trong quá trình lưu dữ liệu
//            Toast.makeText(v.getContext(), "Có lỗi xảy ra khi lưu dữ liệu", Toast.LENGTH_SHORT).show();
//        }
//    });
    myRef.setValue(accoutnMap, new DatabaseReference.CompletionListener() {
        @Override
        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
            Toast.makeText(v.getContext(), "add list success", Toast.LENGTH_SHORT).show();
            holder.button4.setText("DissLike");

        }
    });
}else {


    holder.button4.setText("like");

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference("MyList");
        myRef1.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(v.getContext(), "delete seccess", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


     });

    }
//    private void dangKy(){
//
//        String password = edtPass.getText().toString();
//        String name = edtName.getText().toString();
//        String ho = edtHo.getText().toString();
//        String phone = edtPhone.getText().toString();
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            String uid = user.getUid();
//                            Account acount = new Account(email,name,ho,phone, true);
//                            mDatabase.child(uid).setValue(acount);
//                            Toast.makeText(DangKy.this, "Đăng ký thành công!!!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(DangKy.this, TaikhoanJob.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        else {
//                            Toast.makeText(DangKy.this, "Fail!!!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
    @Override
    public int getItemCount() {
        if(list !=null){
            return  list.size();
        }
        return 0;
    }

    public  class  UserViewholer extends RecyclerView.ViewHolder {

         TextView email;

       ToggleButton button4;



        public UserViewholer(@NonNull View itemView) {

            super(itemView);
            email = (TextView) itemView.findViewById(R.id.email);
          button4 = itemView.findViewById(R.id.like1);

        }
    }
}
