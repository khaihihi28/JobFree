package com.poly.duantotnghiep_jf.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Adapter.ViewPagerAdapter;
import com.poly.duantotnghiep_jf.Fragment.HomeFragment;
import com.poly.duantotnghiep_jf.Fragment.MenuFragment;
import com.poly.duantotnghiep_jf.Helper.CompanyHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Helper.PostHelper;
import com.poly.duantotnghiep_jf.Interface.ItemClickListener;
import com.poly.duantotnghiep_jf.Model.Company;
import com.poly.duantotnghiep_jf.Model.Post;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.ViewHolder.PostViewHolder;
import com.poly.duantotnghiep_jf.viewPager.CustomViewPager;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileCompany extends AppCompatActivity {

    String companyId = "";
    String companyName = "";

    FirebaseDatabase database;
    DatabaseReference post;
    DatabaseReference company;

    Post currentPost;
    Company currentCompany;

    RecyclerView recyclerView_list_post_of_company;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Post, PostViewHolder> adapter;

    ImageView img_top_company,btn_chat;
    CircleImageView img_avatar_company_profile;
    TextView tv_name_company, tv_count_follow, tv_gioiThieu_company, btn_follow,btn_view_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_company);
        bind();

        

        //init firebase
        database = FirebaseDatabase.getInstance();
        post = database.getReference("Post");
        company = database.getReference("Company");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_black);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView_list_post_of_company.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_list_post_of_company.setLayoutManager(layoutManager);

        if(getIntent() != null){
            companyId = getIntent().getStringExtra("companyIdSend");
            companyName = getIntent().getStringExtra("companyNameSend");

        }
        if(!companyId.isEmpty()){
            getDetailCompany(companyId);
            loadListPostOfCompany();
        }
        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickFollow();
            }
        });
        btn_view_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewProfileCompany.this, "Xem thông tin ", Toast.LENGTH_SHORT).show();
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void clickFollow() {
        DatabaseReference companyRef = FirebaseDatabase.getInstance().getReference("Company").child(companyId);
        DatabaseReference followRef = companyRef.child("follower");

        companyRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Company mCompany = mutableData.getValue(Company.class);

                if (mCompany == null) {
                    return Transaction.success(mutableData);
                }
                CompanyHelper.checkIfUserFollowCompany(companyId, FireBaseHelper.getCurrentUserUid(), new CompanyHelper.OnCheckFollowListener() {
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
            btn_follow.setBackground(ContextCompat.getDrawable(ViewProfileCompany.this, R.drawable.bovien_button_viencam));
            btn_follow.setTextColor(ContextCompat.getColor(ViewProfileCompany.this, R.color.orange));
            btn_follow.setText("Unfollow");
        } else {
            btn_follow.setBackground(ContextCompat.getDrawable(ViewProfileCompany.this, R.drawable.bovien_button_cam));
            btn_follow.setTextColor(ContextCompat.getColor(ViewProfileCompany.this, R.color.white));
            btn_follow.setText("Follow");
        }
    }

    private void loadListPostOfCompany() {
        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(post.orderByChild("idCompany").equalTo(companyId), Post.class)
                        .build();
        adapter =
                new FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Post model) {

                        CompanyHelper.getCompanyById(model.getIdCompany(), new CompanyHelper.OnCompanyFetchListener() {
                            @Override
                            public void onCompanyFetch(Company company) {
                                if(company != null || !company.getAvatar().isEmpty() && company.getAvatar() != null && !company.getAvatar().equals("")){
                                    Glide.with(holder.itemView.getContext()).load(company.getAvatar()).into(holder.imgLogo_company_post);
                                }
                                else {
                                    Glide.with(holder.itemView.getContext()).load(R.drawable.avatar_company_default).into(holder.imgLogo_company_post);
                                }
                                holder.name_company_post.setText(company.getName());
                                holder.address_post.setText(company.getAddress());
                            }

                            @Override
                            public void onCompanyNotFound() {

                            }

                            @Override
                            public void onFetchError(String errorMessage) {

                            }
                        });

                        PostHelper.checkIfUserLikedPost(adapter.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
                                new PostHelper.OnCheckLikeListener() {
                                    @Override
                                    public void onPostLiked(boolean isLiked) {
                                        if(isLiked){
                                            Glide.with(holder.itemView.getContext()).load(R.drawable.added_list).into(holder.btn_add_to_list);
                                        }
                                        else {
                                            Glide.with(holder.itemView.getContext()).load("").into(holder.btn_add_to_list);
                                        }
                                    }

                                    @Override
                                    public void onCheckLikeError(String errorMessage) {

                                    }
                                });
                        PostHelper.checkIfUserViewPost(adapter.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
                                new PostHelper.OnCheckViewListener() {
                                    @Override
                                    public void onPostViewed(boolean isViewed) {
                                        if(isViewed){
                                            holder.bg_isNewView.setBackground(null);
                                        }
                                        else {
                                            holder.bg_isNewView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_rounded_rect));
                                        }
                                    }

                                    @Override
                                    public void onCheckVIewError(String errorMessage) {

                                    }
                                });

                        holder.title_post.setText(model.getTitle());
                        holder.luong_post.setText(String.valueOf(model.getLuong()));
                        clickItemTot(holder);

                    }

                    @NonNull
                    @Override
                    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
                        return new PostViewHolder(view);
                    }
                };
        recyclerView_list_post_of_company.setAdapter(adapter);

        // Khi Activity hoặc Fragment được tạo, bắt đầu lắng nghe sự thay đổi trong Realtime Database
        adapter.startListening();
    }
    private void clickItemTot(PostViewHolder holder){
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(ViewProfileCompany.this, DetailPost.class);
                intent.putExtra("postId", adapter.getRef(position).getKey());
                PostHelper.checkIfUserViewPost(adapter.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
                        new PostHelper.OnCheckViewListener() {
                            @Override
                            public void onPostViewed(boolean isViewed) {
                                if(!isViewed){
                                    post.child(adapter.getRef(position).getKey()).child("views").child(FireBaseHelper.getCurrentUserUid()).setValue(true);
                                }
                            }

                            @Override
                            public void onCheckVIewError(String errorMessage) {

                            }
                        });
                DatabaseReference postRef = post.child(adapter.getRef(position).getKey());
                incrementViewCount(postRef);
                startActivity(intent);
            }
        });
    }
    private void incrementViewCount(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Post post = mutableData.getValue(Post.class);

                if (post == null) {
                    // Bài viết không tồn tại hoặc có lỗi dữ liệu
                    return Transaction.success(mutableData);
                }

                // Tăng giá trị viewCount lên 1
                post.setViewCount(post.getViewCount() + 1);

                // Cập nhật lại dữ liệu trong mutableData
                postRef.child("viewCount").setValue(post.getViewCount());

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

    private void getDetailCompany(String companyId){
        company.child(companyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentCompany = snapshot.getValue(Company.class);
                Glide.with(getBaseContext()).load(currentCompany.getAvatar()).into(img_top_company);
                Glide.with(getBaseContext()).load(currentCompany.getAvatar()).into(img_avatar_company_profile);
                tv_name_company.setText(currentCompany.getName());
                tv_count_follow.setText(String.valueOf(currentCompany.getFollowCount()));
                tv_gioiThieu_company.setText(currentCompany.getGioiThieu());
                setUpBtnFollow();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpBtnFollow() {
        CompanyHelper.checkIfUserFollowCompany(companyId, FireBaseHelper.getCurrentUserUid(), new CompanyHelper.OnCheckFollowListener() {
            @Override
            public void onCompanyFollower(boolean followed) {
                if(followed){
                    btn_follow.setBackground(ContextCompat.getDrawable(ViewProfileCompany.this, R.drawable.bovien_button_viencam));
                    btn_follow.setTextColor(ContextCompat.getColor(ViewProfileCompany.this, R.color.orange));
                    btn_follow.setText("Unfollow");
                }
                else {
                    btn_follow.setBackground(ContextCompat.getDrawable(ViewProfileCompany.this, R.drawable.bovien_button_cam));
                    btn_follow.setTextColor(ContextCompat.getColor(ViewProfileCompany.this, R.color.white));
                    btn_follow.setText("Follow");
                }
            }

            @Override
            public void onCheckFollowError(String errorMessage) {

            }
        });
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

    private void bind(){
        recyclerView_list_post_of_company = findViewById(R.id.recyclerView_list_post_of_company);
        img_top_company = findViewById(R.id.img_top_company);
        btn_chat = findViewById(R.id.btn_chat);
        img_avatar_company_profile = findViewById(R.id.img_avatar_company_profile);
        tv_name_company = findViewById(R.id.tv_name_company);
        tv_count_follow = findViewById(R.id.tv_count_follow);
        tv_gioiThieu_company = findViewById(R.id.tv_gioiThieu_company);
        btn_follow = findViewById(R.id.btn_follow);
        btn_view_info = findViewById(R.id.btn_view_info);
    }
}