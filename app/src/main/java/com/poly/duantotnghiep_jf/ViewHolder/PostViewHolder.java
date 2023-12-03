package com.poly.duantotnghiep_jf.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Helper.PostHelper;
import com.poly.duantotnghiep_jf.Interface.ItemClickListener;
import com.poly.duantotnghiep_jf.R;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imgLogo_company_post;
    public ImageView btn_add_to_list;
    public TextView name_company_post;
    public TextView title_post;
    public TextView luong_post;
    public TextView address_post;

    public RelativeLayout bg_isNewView;

    private ItemClickListener itemClickListener;

    private String postKey;
    private int postPosition;



    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        // Ánh xạ các TextView từ itemView
        imgLogo_company_post = itemView.findViewById(R.id.imgLogo_company_post);
        btn_add_to_list = itemView.findViewById(R.id.btn_add_to_list);
        name_company_post = itemView.findViewById(R.id.name_company_post);
        title_post = itemView.findViewById(R.id.title_post);
        luong_post = itemView.findViewById(R.id.luong_post);
        address_post = itemView.findViewById(R.id.address_post);
        bg_isNewView = itemView.findViewById(R.id.bg_isNewView);

//        btn_add_to_list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PostHelper.checkIfUserLikedPost(postKey, FireBaseHelper.getCurrentUserUid(),
//                        new PostHelper.OnCheckLikeListener() {
//                            @Override
//                            public void onPostLiked(boolean isLiked) {
//                                DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("Post").child(postKey).child("likes");
//                                if (isLiked) {
//                                    postRef.child(FireBaseHelper.getCurrentUserUid()).removeValue();
//                                } else {
//                                    postRef.child(FireBaseHelper.getCurrentUserUid()).setValue(true);
//                                }
//                            }
//
//                            @Override
//                            public void onCheckLikeError(String errorMessage) {
//
//                            }
//                        });
//            }
//        });

        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

}
