package com.poly.duantotnghiep_jf.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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
import com.poly.duantotnghiep_jf.Activity.DetailPost;
import com.poly.duantotnghiep_jf.Adapter.HomeSliderAdapter;
import com.poly.duantotnghiep_jf.Helper.CompanyHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Helper.PostHelper;
import com.poly.duantotnghiep_jf.Interface.ItemClickListener;
import com.poly.duantotnghiep_jf.Model.CombinedData;
import com.poly.duantotnghiep_jf.Model.Company;
import com.poly.duantotnghiep_jf.Model.Post;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.ViewHolder.PostViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView_ViecTot, recyclerView_ViecGoiY;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager_ViecGoiY;
    List<String> mListIdCompany = new ArrayList<>();
    List<Post> mListPost = new ArrayList<>();

    FirebaseDatabase database;

    DatabaseReference mRefPost;
    DatabaseReference mRefCompany;

    FirebaseRecyclerAdapter<Post, PostViewHolder> adapter;
    FirebaseRecyclerAdapter<Post, PostViewHolder> adapter_GoiY;


    private ViewPager2 viewPagerSlide;


    private HomeSliderAdapter sliderAdapter;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;

    CombinedData combinedData;

    public static int positonCheck;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init Firebase
        database = FirebaseDatabase.getInstance();
        mRefPost = database.getReference("Post");
        mRefCompany = database.getReference("Company");


        bind(view);

        //Load menu

        recyclerView_ViecTot.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false);
        recyclerView_ViecTot.setLayoutManager(layoutManager);

        //
        recyclerView_ViecGoiY.setHasFixedSize(true);
        layoutManager_ViecGoiY = new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false);
        recyclerView_ViecGoiY.setLayoutManager(layoutManager_ViecGoiY);

        setupSlider();
        loadPost();
//        setListIdCompany();
        loadPostGoiY();

        return view;
    }

    private void loadPost(){
        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(mRefPost.orderByChild("viewCount").limitToLast(15), Post.class)
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
        // Đặt adapter cho RecyclerView
        recyclerView_ViecTot.setAdapter(adapter);

        // Khi Activity hoặc Fragment được tạo, bắt đầu lắng nghe sự thay đổi trong Realtime Database
        adapter.startListening();
    }

    private void setListIdCompany(){
        mListIdCompany.clear();
        CompanyHelper.getCompanyId(new CompanyHelper.OnGetCompanyFetchListener() {
            @Override
            public void onCompanyFetched(List<String> companyList) {
                mListIdCompany.addAll(companyList);
            }

            @Override
            public void onCompanyNotFound() {

            }

            @Override
            public void onFetchError(String errorMessage) {

            }
        });
    }

    private void loadPostGoiY(){

        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(mRefPost.orderByChild("likeCount").limitToLast(21), Post.class)
                        .build();
        adapter_GoiY =
                new FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Post model) {
                        CompanyHelper.getCompanyById(model.getIdCompany(), new CompanyHelper.OnCompanyFetchListener() {
                            @Override
                            public void onCompanyFetch(Company company) {
                                if(company != null || !company.getAvatar().isEmpty() && company.getAvatar() != null){
                                    Glide.with(holder.itemView.getContext()).load(company.getAvatar()).into(holder.imgLogo_company_post);
                                }
                                else {
                                    Glide.with(holder.itemView.getContext()).load(R.drawable.avatar_company_default).into(holder.imgLogo_company_post);
                                }
                                holder.name_company_post.setText(company.getName());
                                holder.address_post.setText(company.getAddress());
//                                adapter_GoiY.notifyDataSetChanged();
                            }

                            @Override
                            public void onCompanyNotFound() {

                            }

                            @Override
                            public void onFetchError(String errorMessage) {

                            }
                        });
                        PostHelper.checkIfUserLikedPost(adapter_GoiY.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
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
                        PostHelper.checkIfUserViewPost(adapter_GoiY.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
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
                        holder.luong_post.setText(String.valueOf(model.getLuong()) + "$");
                        clickItemGoiY(holder);
                    }


                    @NonNull
                    @Override
                    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
                        return new PostViewHolder(view);
                    }
                };
        // Đặt adapter cho RecyclerView
        recyclerView_ViecGoiY.setAdapter(adapter_GoiY);

        // Khi Activity hoặc Fragment được tạo, bắt đầu lắng nghe sự thay đổi trong Realtime Database
        adapter_GoiY.startListening();
    }

    private void clickItemTot(PostViewHolder holder){
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(getActivity(), DetailPost.class);
                intent.putExtra("postId", adapter.getRef(position).getKey());
                PostHelper.checkIfUserViewPost(adapter.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
                        new PostHelper.OnCheckViewListener() {
                            @Override
                            public void onPostViewed(boolean isViewed) {
                                if(!isViewed){
                                    mRefPost.child(adapter.getRef(position).getKey()).child("views").child(FireBaseHelper.getCurrentUserUid()).setValue(true);

                                }
                            }

                            @Override
                            public void onCheckVIewError(String errorMessage) {

                            }
                        });
                DatabaseReference postRef = mRefPost.child(adapter.getRef(position).getKey());
                incrementViewCount(postRef);
                startActivity(intent);
            }
        });
    }
    private void clickItemGoiY(PostViewHolder holder){
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(getActivity(), DetailPost.class);
                intent.putExtra("postId", adapter_GoiY.getRef(position).getKey());
                PostHelper.checkIfUserViewPost(adapter_GoiY.getRef(position).getKey(), FireBaseHelper.getCurrentUserUid(),
                        new PostHelper.OnCheckViewListener() {
                            @Override
                            public void onPostViewed(boolean isViewed) {
                                if(!isViewed){
                                    mRefPost.child(adapter_GoiY.getRef(position).getKey()).child("views").child(FireBaseHelper.getCurrentUserUid()).setValue(true);
                                }
                            }

                            @Override
                            public void onCheckVIewError(String errorMessage) {

                            }
                        });
                DatabaseReference postRef = mRefPost.child(adapter_GoiY.getRef(position).getKey());
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

    private void setupSlider() {
        List<Integer> imageList = Arrays.asList(
                R.drawable.card_image_1,
                R.drawable.card_image_2,
                R.drawable.card_image_3,
                R.drawable.card_image_4
        );

        sliderAdapter = new HomeSliderAdapter(imageList);
        viewPagerSlide.setAdapter(sliderAdapter);

        // Auto chuyển đổi slide mỗi 3 giây
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPagerSlide.getCurrentItem();
                int totalItems = imageList.size();
                int nextItem = (currentItem + 1) % totalItems;
                viewPagerSlide.setCurrentItem(nextItem);
                handler.postDelayed(this, 3000); // 3000 milliseconds = 3 seconds
            }
        };
        // Khởi động auto chuyển đổi
        handler.postDelayed(runnable, 3000); // 3000 milliseconds = 3 seconds
    }

    @Override
    public void onDestroyView() {
        // Dừng auto chuyển đổi khi Fragment bị hủy
        handler.removeCallbacks(runnable);
        super.onDestroyView();
    }
    private void bind(View view){
        viewPagerSlide = view.findViewById(R.id.viewPagerSlide);
        recyclerView_ViecTot = view.findViewById(R.id.recyclerView_viecTot);
        recyclerView_ViecGoiY = view.findViewById(R.id.recyclerView_viecGoiY);
    }
}
