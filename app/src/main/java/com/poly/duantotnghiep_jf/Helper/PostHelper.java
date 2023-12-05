package com.poly.duantotnghiep_jf.Helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListUtil;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Model.Company;
import com.poly.duantotnghiep_jf.Model.Post;

import java.util.ArrayList;
import java.util.List;
public class PostHelper {
    public static void checkIfUserLikedPost(String postId, String uid, OnCheckLikeListener listener) {
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("Post").child(postId).child("likes");

        postRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(uid)) {
                    // Người dùng đã thích bài viết
                    listener.onPostLiked(true);
                } else {
                    // Người dùng chưa thích bài viết
                    listener.onPostLiked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                listener.onCheckLikeError(databaseError.getMessage());
            }
        });
    }

    public interface OnCheckLikeListener {
        void onPostLiked(boolean isLiked);

        void onCheckLikeError(String errorMessage);
    }

    public static void checkIfUserViewPost(String postId, String uid, OnCheckViewListener listener) {
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("Post").child(postId).child("views");

        postRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(uid)) {
                    // Người dùng đã xem bài viết
                    listener.onPostViewed(true);
                } else {
                    // Người dùng chưa xem bài viết
                    listener.onPostViewed(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                listener.onCheckVIewError(databaseError.getMessage());
            }
        });
    }

    public interface OnCheckViewListener {
        void onPostViewed(boolean isViewed);

        void onCheckVIewError(String errorMessage);
    }

    public static void checkIfUserUngTuyenPost(String postId, String uid, OnCheckUngTuyenListener listener) {
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("Post").child(postId).child("ungTuyen");

        postRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(uid)) {
                    // Người dùng đã thích bài viết
                    listener.onPostUngTuyen(true);
                } else {
                    // Người dùng chưa thích bài viết
                    listener.onPostUngTuyen(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                listener.onCheckUngTuyenError(databaseError.getMessage());
            }
        });
    }

    public interface OnCheckUngTuyenListener {
        void onPostUngTuyen(boolean isRecruited);

        void onCheckUngTuyenError(String errorMessage);
    }
}
