package com.poly.duantotnghiep_jf.Helper;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Activity.DangNhap;
import com.poly.duantotnghiep_jf.Activity.ThuThapThongTin;
import com.poly.duantotnghiep_jf.MainActivity;

public class FireBaseHelper {
    ///------------------ACOUNT---------------------------

    //Check Login
    public static boolean isUserLoggedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        return currentUser != null;
    }
    //get currentuser
    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    //get uid user(id)
    public static String getCurrentUserUid() {
        FirebaseUser currentUser = getCurrentUser();
        return currentUser != null ? currentUser.getUid() : "";
    }

    //check newAccoutn
    public static void checkIsNewAccount(OnIsNewAccountCheckListener listener) {
        DatabaseReference isNewAccountReference = FirebaseDatabase.getInstance().getReference("Account").child(getCurrentUserUid()).child("newAccount");
        isNewAccountReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isNewAccount = false;
                if (snapshot.exists() && snapshot.getValue() instanceof Boolean) {
                    isNewAccount = (boolean) snapshot.getValue();
                }
                listener.onIsNewAccountCheck(isNewAccount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onIsNewAccountCheck(false);
            }
        });
    }
    // Interface để trả kết quả kiểm tra về
    public interface OnIsNewAccountCheckListener {
        void onIsNewAccountCheck(boolean isNewAccount);
    }



}
