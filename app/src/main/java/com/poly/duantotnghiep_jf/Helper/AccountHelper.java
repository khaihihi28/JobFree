package com.poly.duantotnghiep_jf.Helper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Model.Account;

public class AccountHelper {

    public static void getAccountByUid(String uid, ValueEventListener valueEventListener) {
        DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference("Account").child(uid);
        accountRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public static void checkIsActiveAccount(AccountHelper.OnIsActiveAccountCheckListener listener) {
        DatabaseReference manegeCompanyRef = FirebaseDatabase.getInstance().getReference("Account").child(FireBaseHelper.getCurrentUserUid()).child("activeAccount");
        manegeCompanyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isActive = false;
                if (snapshot.exists() && snapshot.getValue() instanceof Boolean) {
                    isActive = (boolean) snapshot.getValue();
                }
                listener.onIsActiveAccountCheck(isActive);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onIsActiveAccountCheck(false);
            }
        });
    }
    // Interface để trả kết quả kiểm tra về
    public interface OnIsActiveAccountCheckListener {
        void onIsActiveAccountCheck(boolean isActive);
    }

    public static void checkIsManageCompany(AccountHelper.OnIsManageCompanyCheckListener listener) {
        DatabaseReference manegeCompanyRef = FirebaseDatabase.getInstance().getReference("Account").child(FireBaseHelper.getCurrentUserUid()).child("manegeCompany");
        manegeCompanyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isManageCompany = false;
                if (snapshot.exists() && snapshot.getValue() instanceof Boolean) {
                    isManageCompany = (boolean) snapshot.getValue();
                }
                listener.onIsManageCompanyCheck(isManageCompany);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onIsManageCompanyCheck(false);
            }
        });
    }
    // Interface để trả kết quả kiểm tra về
    public interface OnIsManageCompanyCheckListener {
        void onIsManageCompanyCheck(boolean isManageCompany);
    }


    //Coin
    public interface CoinCallback {
        void onCoinReceived(Long coin);

        void onError(String errorMessage);
    }

    public static void getCoinForAccount(String accountId, final CoinCallback callback) {
        // Khởi tạo Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Tham chiếu đến node "Account" trong cơ sở dữ liệu
        DatabaseReference accountRef = database.getReference("Account");

        // Thực hiện truy vấn để lấy dữ liệu của tài khoản cụ thể
        accountRef.child(accountId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Kiểm tra xem có dữ liệu hay không
                if (dataSnapshot.exists()) {
                    // Lấy giá trị của thuộc tính "coin"
                    Long coin = dataSnapshot.child("coin").getValue(Long.class);

                    // Kiểm tra xem coin có tồn tại hay không
                    if (coin != null) {
                        callback.onCoinReceived(coin);
                    } else {
                        callback.onError("Không tìm thấy thông tin coin cho tài khoản " + accountId);
                    }
                } else {
                    callback.onError("Không tìm thấy tài khoản có ID " + accountId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError("Lỗi khi đọc dữ liệu từ Firebase: " + databaseError.getMessage());
            }
        });
    }
}
