package com.poly.duantotnghiep_jf.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Helper.CompanyHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Helper.PostHelper;
import com.poly.duantotnghiep_jf.Model.Company;
import com.poly.duantotnghiep_jf.Model.Post;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.Util.ViewProfileCompany;

import java.util.Objects;

public class DetailPost extends AppCompatActivity {

    //bind View
    ImageView img_avatar_company, iv_company_avatar,btnAddToList;
    TextView tv_Title_post,tv_luong_port,tv_timeWork,tv_address_post,btn_UngTuyen,tv_company_name,tv_count_post,btnFollowCompany,
            tv_count_soLuong,tv_viewCount,tv_likeCount,tv_daiNgo,tv_moTaCongViec,tv_address, tv_timePost,tv_yeuCau;
    RelativeLayout ll_avatar_company;
    LinearLayout ll_post_details;

    FirebaseDatabase database;
    DatabaseReference post;
    DatabaseReference company;
    String postId = "";
    Post currentPost;
    Company currentCompany;

    public static int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        bind();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        //init firebase
        database = FirebaseDatabase.getInstance();
        post = database.getReference("Post");
        company = database.getReference("Company");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if(getIntent() != null){
            postId = getIntent().getStringExtra("postId");
        }
        if(!postId.isEmpty()){
            getDetailPost(postId);
        }

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddTolist();
            }
        });

        btn_UngTuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickUngTuyen();
            }
        });

        btnFollowCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickFollow();
            }
        });


        ll_avatar_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickShowProfileCompany();
            }
        });
        ll_post_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickShowProfileCompany();
            }
        });

    }

    private void clickShowProfileCompany() {
        Intent intent = new Intent(DetailPost.this, ViewProfileCompany.class);
        intent.putExtra("companyIdSend", currentPost.getIdCompany());
        intent.putExtra("companyNameSend", currentCompany.getName());
        startActivity(intent);
    }

    private void clickFollow() {
        DatabaseReference companyRef = FirebaseDatabase.getInstance().getReference("Company").child(currentPost.getIdCompany());
        DatabaseReference followRef = companyRef.child("follower");

        companyRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Company mCompany = mutableData.getValue(Company.class);

                if (mCompany == null) {
                    return Transaction.success(mutableData);
                }
                CompanyHelper.checkIfUserFollowCompany(currentPost.getIdCompany(), FireBaseHelper.getCurrentUserUid(), new CompanyHelper.OnCheckFollowListener() {
                    @Override
                    public void onCompanyFollower(boolean followed) {
                        if(followed) {
                            followRef.child(FireBaseHelper.getCurrentUserUid()).removeValue();
                            mCompany.setFollowCount(mCompany.getFollowCount() -1);
                            companyRef.child("followCount").setValue(mCompany.getFollowCount());
                        }
                        else {
                            followRef.child(FireBaseHelper.getCurrentUserUid()).setValue(true);
                            mCompany.setFollowCount(mCompany.getFollowCount() + 1);
                            companyRef.child("followCount").setValue(mCompany.getFollowCount());
                        }
                        updateFollowButtonUI(!followed);
                    }

                    @Override
                    public void onCheckFollowError(String errorMessage) {

                    }
                }
            );



                // Trả về thành công
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (error != null) {
                    // Xử lý lỗi khi thực hiện giao dịch
                }
            }
        });
    }
    private void updateFollowButtonUI(boolean followed) {
        if(followed) {
            btnFollowCompany.setBackground(ContextCompat.getDrawable(DetailPost.this, R.drawable.bovien_button_viencam));
            btnFollowCompany.setTextColor(ContextCompat.getColor(DetailPost.this, R.color.orange));
            btnFollowCompany.setText("Followed");
        } else {
            btnFollowCompany.setBackground(ContextCompat.getDrawable(DetailPost.this, R.drawable.bovien_button_cam));
            btnFollowCompany.setTextColor(ContextCompat.getColor(DetailPost.this, R.color.white));
            btnFollowCompany.setText("Follow");
        }
    }

    private void clickUngTuyen() {
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("Post").child(postId);
        DatabaseReference ungTuyenRef = postRef.child("ungTuyen");

        postRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Post post = mutableData.getValue(Post.class);

                if (post == null) {
                    // Bài viết không tồn tại hoặc có lỗi dữ liệu
                    return Transaction.success(mutableData);
                }
                PostHelper.checkIfUserUngTuyenPost(postId, FireBaseHelper.getCurrentUserUid(), new PostHelper.OnCheckUngTuyenListener() {
                    @Override
                    public void onPostUngTuyen(boolean isRecruited) {
                        if(isRecruited){
                            ungTuyenRef.child(FireBaseHelper.getCurrentUserUid()).removeValue();
                            Toast.makeText(DetailPost.this, "Bạn đã hủy ứng tuyển cho công việc này.", Toast.LENGTH_SHORT).show();
                            post.setUngTuyenCount(post.getUngTuyenCount() -1);
                            postRef.child("ungTuyenCount").setValue(post.getUngTuyenCount());
                        }
                        else {
                            Toast.makeText(DetailPost.this, "Ứng tuyển thành công", Toast.LENGTH_SHORT).show();
                            ungTuyenRef.child(FireBaseHelper.getCurrentUserUid()).setValue(true);
                            post.setUngTuyenCount(post.getUngTuyenCount() + 1);
                            postRef.child("ungTuyenCount").setValue(post.getUngTuyenCount());
                        }
                    }

                    @Override
                    public void onCheckUngTuyenError(String errorMessage) {

                    }
                });



                // Trả về thành công
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (error != null) {
                    // Xử lý lỗi khi thực hiện giao dịch
                }
            }
        });
    }

    private void clickAddTolist() {
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("Post").child(postId);
        DatabaseReference likeRef = postRef.child("likes");
        postRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Post post = mutableData.getValue(Post.class);

                if (post == null) {
                    // Bài viết không tồn tại hoặc có lỗi dữ liệu
                    return Transaction.success(mutableData);
                }
                PostHelper.checkIfUserLikedPost(postId, FireBaseHelper.getCurrentUserUid(),
                        new PostHelper.OnCheckLikeListener() {
                            @Override
                            public void onPostLiked(boolean isLiked) {
                                if(isLiked){
                                    likeRef.child(FireBaseHelper.getCurrentUserUid()).removeValue();
                                    Toast.makeText(DetailPost.this, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                                    post.setLikeCount(post.getLikeCount() -1);
                                    postRef.child("likeCount").setValue(post.getLikeCount());
                                }
                                else {
                                    likeRef.child(FireBaseHelper.getCurrentUserUid()).setValue(true);
                                    Toast.makeText(DetailPost.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                                    post.setLikeCount(post.getLikeCount() + 1);
                                    postRef.child("likeCount").setValue(post.getLikeCount());
                                }
                            }

                            @Override
                            public void onCheckLikeError(String errorMessage) {

                            }
                        });



                // Trả về thành công
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (error != null) {
                    // Xử lý lỗi khi thực hiện giao dịch
                }
            }
        });

    }

    private void getDetailPost(String postId){
        post.child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentPost = snapshot.getValue(Post.class);
                tv_Title_post.setText(currentPost.getTitle());
                tv_address_post.setText(currentPost.getAddress());
                tv_luong_port.setText(currentPost.getLuong()+"$");
                tv_timeWork.setText(currentPost.getThoiGianLamViec());
                tv_timePost.setText(currentPost.getTimestamp());
                tv_count_soLuong.setText(currentPost.getUngTuyenCount() + "/" + currentPost.getSoLuong());
                tv_viewCount.setText(currentPost.getViewCount()+"");
                tv_likeCount.setText(currentPost.getLikeCount()+"");
                tv_daiNgo.setText(currentPost.getBonus());
                tv_moTaCongViec.setText(currentPost.getMoTa());
                tv_address.setText(currentPost.getAddress());
                tv_yeuCau.setText(currentPost.getYeuCau());
                company.child(currentPost.getIdCompany()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        currentCompany = snapshot.getValue(Company.class);
                        Glide.with(getBaseContext()).load(currentCompany.getAvatar()).into(img_avatar_company);
                        Glide.with(getBaseContext()).load(currentCompany.getAvatar()).into(iv_company_avatar);
                        tv_company_name.setText(currentCompany.getName());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                setUpBtnAddToList();
                setUpBtnUngTuyen();
                setUpBtnFollow();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpBtnFollow() {
        CompanyHelper.checkIfUserFollowCompany(currentPost.getIdCompany(), FireBaseHelper.getCurrentUserUid(), new CompanyHelper.OnCheckFollowListener() {
            @Override
            public void onCompanyFollower(boolean followed) {
                if(followed){
                    btnFollowCompany.setBackground(ContextCompat.getDrawable(DetailPost.this, R.drawable.bovien_button_viencam));
                    btnFollowCompany.setTextColor(ContextCompat.getColor(DetailPost.this, R.color.orange));
                    btnFollowCompany.setText("Followed");
                }
                else {
                    btnFollowCompany.setBackground(ContextCompat.getDrawable(DetailPost.this, R.drawable.bovien_button_cam));
                    btnFollowCompany.setTextColor(ContextCompat.getColor(DetailPost.this, R.color.white));
                    btnFollowCompany.setText("Follow");
                }
            }

            @Override
            public void onCheckFollowError(String errorMessage) {

            }
        });
    }

    private void setUpBtnUngTuyen() {
        PostHelper.checkIfUserUngTuyenPost(postId, FireBaseHelper.getCurrentUserUid(), new PostHelper.OnCheckUngTuyenListener() {
            @Override
            public void onPostUngTuyen(boolean isRecruited) {
                if(isRecruited){
                    btn_UngTuyen.setBackground(ContextCompat.getDrawable(DetailPost.this, R.drawable.bovien_button_viencam));
                    btn_UngTuyen.setTextColor(ContextCompat.getColor(DetailPost.this, R.color.orange));
                    btn_UngTuyen.setText("Đã ứng tuyển");
                }
                else {
                    btn_UngTuyen.setBackground(ContextCompat.getDrawable(DetailPost.this, R.drawable.bovien_button_cam));
                    btn_UngTuyen.setTextColor(ContextCompat.getColor(DetailPost.this, R.color.white));
                    btn_UngTuyen.setText("Ứng tuyển");
                }
            }

            @Override
            public void onCheckUngTuyenError(String errorMessage) {

            }
        });
    }

    private void setUpBtnAddToList(){
        PostHelper.checkIfUserLikedPost(postId, FireBaseHelper.getCurrentUserUid(),
                new PostHelper.OnCheckLikeListener() {
                    @Override
                    public void onPostLiked(boolean isLiked) {
                        if(isLiked){
                            Glide.with(getBaseContext()).load(R.drawable.added_list).into(btnAddToList);
                        }
                        else {
                            Glide.with(getBaseContext()).load(R.drawable.to_add_list).into(btnAddToList);
                        }
                    }

                    @Override
                    public void onCheckLikeError(String errorMessage) {

                    }
                });
    }

    private void bind(){
        img_avatar_company = findViewById(R.id.img_avatar_company);
        iv_company_avatar = findViewById(R.id.iv_company_avatar);
        btnAddToList = findViewById(R.id.btnAddToList);
        tv_Title_post = findViewById(R.id.tv_Title_post);
        tv_luong_port = findViewById(R.id.tv_luong_port);
        tv_timeWork = findViewById(R.id.tv_timeWork);
        tv_address_post = findViewById(R.id.tv_address_post);
        btn_UngTuyen = findViewById(R.id.btn_UngTuyen);
        tv_company_name = findViewById(R.id.tv_company_name);
        tv_count_post = findViewById(R.id.tv_count_post);
        btnFollowCompany = findViewById(R.id.btnFollowCompany);
        tv_count_soLuong = findViewById(R.id.tv_count_soLuong);
        tv_viewCount = findViewById(R.id.tv_viewCount);
        tv_likeCount = findViewById(R.id.tv_likeCount);
        tv_daiNgo = findViewById(R.id.tv_daiNgo);
        tv_moTaCongViec = findViewById(R.id.tv_moTaCongViec);
        tv_address = findViewById(R.id.tv_address);
        tv_timePost = findViewById(R.id.tv_timePost);
        tv_yeuCau = findViewById(R.id.tv_yeuCau);
        ll_avatar_company = findViewById(R.id.ll_avatar_company);
        ll_post_details = findViewById(R.id.ll_post_details);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Xử lý sự kiện khi item được chọn trên thanh toolbar
        switch (item.getItemId()) {
            case android.R.id.home: // ID của nút "Back"
                // Khi nút "Back" được nhấn, kết thúc Activity hiện tại
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}