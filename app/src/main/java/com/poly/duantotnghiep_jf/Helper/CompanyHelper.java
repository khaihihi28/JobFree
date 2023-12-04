package com.poly.duantotnghiep_jf.Helper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyHelper {
    public interface OnCompanyFetchListener {
        void onCompanyFetch(Company company);
        void onCompanyNotFound();
        void onFetchError(String errorMessage);

    }
    public static void getCompanyById(String companyId, OnCompanyFetchListener listener) {
        DatabaseReference companyRef = FirebaseDatabase.getInstance().getReference("Company");

        companyRef.child(companyId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Company company = dataSnapshot.getValue(Company.class);
                    if (company != null) {
                        listener.onCompanyFetch(company);
                    } else {
                        listener.onFetchError("Failed to parse Company object");
                    }
                } else {
                    listener.onCompanyNotFound();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFetchError(databaseError.getMessage());
            }
        });
    }


    public interface OnGetCompanyFetchListener {
        void onCompanyFetched(List<String> companyList);
        void onCompanyNotFound();
        void onFetchError(String errorMessage);
    }
    public static void getCompanyId(OnGetCompanyFetchListener listener) {
        List<String> mlistCompany = new ArrayList<>();
        mlistCompany.clear();
        DatabaseReference companyRef = FirebaseDatabase.getInstance().getReference("Company");

        companyRef.orderByChild("pheDuyet").equalTo(true).limitToFirst(15).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String companyId = snapshot.getKey();
                        if (companyId != null) {
                            mlistCompany.add(companyId);
                        }
                    }
                    listener.onCompanyFetched(mlistCompany);
                } else {
                    listener.onCompanyNotFound();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFetchError(databaseError.getMessage());
            }
        });
    }


    public static void checkIfUserFollowCompany(String idCompany, String uid, OnCheckFollowListener listener) {
        DatabaseReference companyRef = FirebaseDatabase.getInstance().getReference("Company").child(idCompany).child("follower");

        companyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(uid)) {
                    // Người dùng đã thích bài viết
                    listener.onCompanyFollower(true);
                } else {
                    // Người dùng chưa thích bài viết
                    listener.onCompanyFollower(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                listener.onCheckFollowError(databaseError.getMessage());
            }
        });
    }

    public interface OnCheckFollowListener {
        void onCompanyFollower(boolean followed);

        void onCheckFollowError(String errorMessage);
    }


}
