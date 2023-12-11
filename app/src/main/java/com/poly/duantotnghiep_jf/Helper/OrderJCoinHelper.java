package com.poly.duantotnghiep_jf.Helper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Model.OrderJCoin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderJCoinHelper {
    public static void makePayment(String accountId, String messagePayJCoin, final PaymentCallback callback) {
        DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference accountRef = rootRef.child("Account").child(accountId);
        final DatabaseReference orderJCoinRef = rootRef.child("OrderJCoin").push();

        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Long currentCoin = dataSnapshot.child("coin").getValue(Long.class);

                    if (currentCoin != null && currentCoin >= 100) {
                        LocalDateTime currentDateTime = null;
                        DateTimeFormatter formatter = null;
                        String formattedDateTime = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            currentDateTime = LocalDateTime.now();
                            formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy ");
                            formattedDateTime = currentDateTime.format(formatter);
                        }

                        Long newCoin = currentCoin - 100;

                        // Cập nhật giá trị coin mới vào tài khoản
                        accountRef.child("coin").setValue(newCoin);

                        // Lưu thông tin thanh toán vào bảng orderJCoin
                        OrderJCoin orderJCoin = new OrderJCoin(accountId, 100,messagePayJCoin, formattedDateTime);
                        orderJCoinRef.setValue(orderJCoin);

                        // Gọi callback khi thanh toán thành công
                        callback.onPaymentSuccess();
                    } else {
                        // Gọi callback khi không đủ coin để trừ
                        callback.onPaymentError("Không đủ coin để thanh toán");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gọi callback khi có lỗi truy cập Firebase Realtime Database
                callback.onPaymentError("Lỗi truy cập Firebase Realtime Database");
            }
        });
    }
    public interface PaymentCallback {
        void onPaymentSuccess();
        void onPaymentError(String errorMessage);
    }

}
