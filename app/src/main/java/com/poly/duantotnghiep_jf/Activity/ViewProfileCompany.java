package com.poly.duantotnghiep_jf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import com.poly.duantotnghiep_jf.Interface.ItemClickListener;
import com.poly.duantotnghiep_jf.Model.Company;
import com.poly.duantotnghiep_jf.Model.Post;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.ViewHolder.PostViewHolder;

import java.util.Objects;

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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    }
}