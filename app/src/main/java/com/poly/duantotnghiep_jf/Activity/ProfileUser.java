package com.poly.duantotnghiep_jf.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Model.Account;
import com.poly.duantotnghiep_jf.R;

import java.util.Objects;

public class ProfileUser extends AppCompatActivity {

    private Account account;
    private ImageView img_avatar_company_profile, btn_change_avatar;
    private TextView tv_name_user_profile, tv_ChucVu, tv_kinhNghiem, tv_email_profile_user, tv_bangCap_profile;
    private DatabaseReference myRef;
    private LinearLayout ln_edit,ln_back;
    private Dialog dialog;


    private final ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        selectImage(selectedImageUri);
                    }
                }
            });

    private void selectImage(Uri selectedImageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("avatar/" + FireBaseHelper.getCurrentUserUid() + ".jpg");
        UploadTask uploadTask = storageRef.putFile(selectedImageUri);
        uploadTask
                .addOnFailureListener(e -> showSnackbar(btn_change_avatar,"Tải ảnh thất bại"))
                .addOnSuccessListener(taskSnapshot -> taskSnapshot.getStorage().getDownloadUrl()
                        .addOnSuccessListener(downloadUri -> {
                            String imageURL = downloadUri.toString();
                            Glide.with(getBaseContext())
                                    .load(imageURL)
                                    .into(img_avatar_company_profile);
                            updateField("avatar",imageURL);
                        })
                        .addOnFailureListener(e -> showSnackbar(img_avatar_company_profile,"Lấy URL ảnh thất bại")));

    }

    private void updateField(String nameField,String imageURL) {
        myRef.child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(nameField).setValue(imageURL);
    }

    private void showSnackbar(View view,String message) {
        Snackbar.make(view , message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        myRef = FirebaseDatabase.getInstance().getReference("Account");
        account = (Account) getIntent().getSerializableExtra("account");
        initView();
        changeAvatar();
        edit();
        back();
    }

    private void back() {
        ln_back = findViewById(R.id.ln_back);
        ln_back.setOnClickListener(v -> {
           finish();
        });
    }

    private void edit() {
        ln_edit.setOnClickListener(v -> showModalEdit());
    }

    private void showModalEdit() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_info_user, null);
        EditText edt_ten = view.findViewById(R.id.edt_ten);
        EditText edt_major = view.findViewById(R.id.edt_major);
        EditText edt_exp = view.findViewById(R.id.edt_exp);
        EditText edt_education = view.findViewById(R.id.edt_education);
        Button btn_update = view.findViewById(R.id.btn_update);
        edt_ten.setText(account.getName());
        edt_major.setText(account.getChuyenNganh());
        edt_exp.setText(account.getKinhNghiem());
        edt_education.setText(account.getTrinhDo());
        btn_update.setOnClickListener(v -> {
            String ten = edt_ten.getText().toString().trim();
            String nganh = edt_major.getText().toString().trim();
            String kn = edt_exp.getText().toString().trim();
            String td = edt_education.getText().toString().trim();
            updateField("name",ten);
            updateField("chuyenNganh",nganh);
            updateField("kinhNghiem",kn);
            updateField("trinhDo",td);
            account.setName(ten);
            account.setChuyenNganh(nganh);
            account.setKinhNghiem(kn);
            account.setTrinhDo(td);
            showAccount();
            dialog.dismiss();
        });
        alert.setView(view);
        dialog = alert.create();
        dialog.show();
    }

    private void changeAvatar() {
        btn_change_avatar.setOnClickListener(view -> imageChooser());
    }


    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    private void initView() {
        img_avatar_company_profile = findViewById(R.id.img_avatar_company_profile);
        btn_change_avatar = findViewById(R.id.btn_change_avatar);
        tv_name_user_profile = findViewById(R.id.tv_name_user_profile);
        tv_ChucVu = findViewById(R.id.tv_ChucVu);
        tv_kinhNghiem = findViewById(R.id.tv_kinhNghiem);
        tv_email_profile_user = findViewById(R.id.tv_email_profile_user);
        tv_bangCap_profile = findViewById(R.id.tv_bangCap_profile);
        ln_edit = findViewById(R.id.ln_edit);


        if (account.getAvatar().equals("R.drawable.profile_img_default")) {
            img_avatar_company_profile.setImageResource(R.drawable.profile_img_default);
        } else {
            Glide.with(getBaseContext())
                    .load(account.getAvatar())
                    .error(R.drawable.profile_img_default)
                    .into(img_avatar_company_profile);
        }
        showAccount();
    }

    @SuppressLint("SetTextI18n")
    private void showAccount(){
        tv_name_user_profile.setText(account.getName());
        tv_ChucVu.setText(account.getChuyenNganh());
        tv_kinhNghiem.setText(account.getKinhNghiem());
        tv_email_profile_user.setText(account.getEmail());
        tv_bangCap_profile.setText(account.getTrinhDo());
    }
}