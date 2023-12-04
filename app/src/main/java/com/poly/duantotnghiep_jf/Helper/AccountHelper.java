package com.poly.duantotnghiep_jf.Helper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountHelper {

    public static void getAccountByUid(String uid, ValueEventListener valueEventListener) {
        DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference("Account").child(uid);
        accountRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public static void checkIsManegeCompany(OnIsManegeCompanyCheckListener listener) {
        DatabaseReference manegeCompanyRef = FirebaseDatabase.getInstance().getReference("Account").child(FireBaseHelper.getCurrentUserUid()).child("manegeCompany");
        manegeCompanyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean manegeCompany = false;
                if (snapshot.exists() && snapshot.getValue() instanceof Boolean) {
                    manegeCompany = (boolean) snapshot.getValue();
                }
                listener.onIsManegeCompanyCheck(manegeCompany);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onIsManegeCompanyCheck(false);
            }
        });
    }
    // Interface để trả kết quả kiểm tra về
    public interface OnIsManegeCompanyCheckListener {
        void onIsManegeCompanyCheck(boolean manegeCompany);
    }
}
