package com.poly.duantotnghiep_jf.BottomDialogFragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.Helper.AccountHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Helper.OrderJCoinHelper;
import com.poly.duantotnghiep_jf.MainActivity;
import com.poly.duantotnghiep_jf.R;
import com.squareup.picasso.Picasso;

public class BottomUnlockAccountFragment extends BottomSheetDialogFragment {
    TextView tv_showMore,unlock_message,tv_have_coin, tv_erro_unlock_by_jcoin, btn_unlick_by_jcoin, btn_pay_zalopay,btn_pay_momo, btn_X;
    Spinner caidatban1;
    ImageView imageView;
    private boolean isExpanded = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_unlock_account, container, false);
        caidatban1 = (Spinner) view.findViewById(R.id.caidatbanspinner);
        unlock_message = view.findViewById(R.id.unlock_message);

        tv_erro_unlock_by_jcoin = view.findViewById(R.id.tv_erro_unlock_by_jcoin);
        btn_unlick_by_jcoin = view.findViewById(R.id.bn_unlick_by_jcoin);
        imageView = view.findViewById(R.id.imageView);
        btn_X = view.findViewById(R.id.btn_X);





        // Định nghĩa mảng giá trị cho Spinner
        String[] items = {"", "1 Tháng", "3 Tháng","6 Tháng"};

// Tạo ArrayAdapter để kết nối mảng giá trị với Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);

// Thiết lập giao diện cho danh sách thả xuống
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


// Đặt ArrayAdapter cho Spinner
        caidatban1.setAdapter(adapter);

        caidatban1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id1) {
                // Xử lý khi một mục được chọn
                String selectedValue = items[position];

                if (selectedValue == "") {


                } else if (selectedValue == "1 Tháng") {
                    int tien = 80000;

                    String imageUrl = "https://api.vietqr.io/image/970422-999993092003-mpJvWLi.jpg?accountName=NGUYEN%20HUU%20THINH&amount="+tien+"&addInfo=Thanh%20toan%201 thang";
                    Picasso.get().load(imageUrl).into(imageView);
                }else if (selectedValue == "3 Tháng") {
                    int tien = 150000;

                    String imageUrl = "https://api.vietqr.io/image/970422-999993092003-mpJvWLi.jpg?accountName=NGUYEN%20HUU%20THINH&amount="+tien+"&addInfo=Thanh%20toan%203 thang";
                    Picasso.get().load(imageUrl).into(imageView);

                } else if (selectedValue == "6 Tháng") {
                    int tien = 220000;

                    String imageUrl = "https://api.vietqr.io/image/970422-999993092003-mpJvWLi.jpg?accountName=NGUYEN%20HUU%20THINH&amount="+tien+"&addInfo=Thanh%20toan%206 thang";
                    Picasso.get().load(imageUrl).into(imageView);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Xử lý khi không có mục nào được chọn
            }
        });

        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Tính toán chiều cao cần đặt (90% của màn hình)
        int desiredHeight = (int) (screenHeight * 0.9);

        // Lấy LayoutParams của RelativeLayout
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.findViewById(R.id.llContent).getLayoutParams();

        // Đặt chiều cao cho RelativeLayout
        layoutParams.height = desiredHeight;

        // Đặt lại LayoutParams
        view.findViewById(R.id.llContent).setLayoutParams(layoutParams);

        setCoin();

        btn_unlick_by_jcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paybyJCoin();

            }
        });





        btn_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        // TODO: Thực hiện các thao tác khác tại đây, chẳng hạn như xử lý nút X và lưu.

        return view;
    }

    private void paybyJCoin(){
        ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Vui lòng chờ...");
        mDialog.show();
        OrderJCoinHelper.makePayment(FireBaseHelper.getCurrentUserUid(), "Thanh toán JCoin",
                new OrderJCoinHelper.PaymentCallback() {
                    @Override
                    public void onPaymentSuccess() {
                        mDialog.dismiss();
                        updateActiveAccount(FireBaseHelper.getCurrentUserUid(), true);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Mở tài khoản thành công")
                                .setMessage("Chúc mừng bạn đã mở tài khoản doanh nghiệp thành công! \n Giờ đây bạn có thể trải nghiệm chức năng mở doanh nghiệp và quản lý doanh nghiệp của mình cùng nhiều chức năng khác đang chờ bạn.\nChúc bạn có trải nghiệm thật tốt khi sử dụng dịch vụ của chúng tôi.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dismiss();
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                })
                                .show();
                    }

                    @Override
                    public void onPaymentError(String errorMessage) {

                    }
                });
    }
    private static void updateActiveAccount(String accountId, boolean newValue) {
        // Thực hiện kết nối đến Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference accountRef = database.getReference("Account").child(accountId).child("activeAccount");

        // Đặt giá trị mới cho thuộc tính manegeCompany
        accountRef.setValue(newValue);
    }
    private void setCoin (){
        AccountHelper.getCoinForAccount(FireBaseHelper.getCurrentUserUid(), new AccountHelper.CoinCallback() {
            @Override
            public void onCoinReceived(Long coin) {
                tv_have_coin.setText(String.valueOf(coin));
                if(coin < 100){
                    tv_erro_unlock_by_jcoin.setVisibility(View.VISIBLE);
                    btn_unlick_by_jcoin.setVisibility(View.GONE);
                }
                else {
                    tv_erro_unlock_by_jcoin.setVisibility(View.GONE);
                    btn_unlick_by_jcoin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }
}
