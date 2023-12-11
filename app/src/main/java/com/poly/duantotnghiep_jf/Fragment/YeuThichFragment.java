package com.poly.duantotnghiep_jf.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.duantotnghiep_jf.Helper.CompanyHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Helper.PostHelper;
import com.poly.duantotnghiep_jf.Model.Company;
import com.poly.duantotnghiep_jf.Model.Post;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.ViewHolder.PostViewHolder;

public class YeuThichFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference favoriteList;
    FirebaseRecyclerAdapter<Post, PostViewHolder> adapter;
    public YeuThichFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yeuthich, container, false);

        //init Firebase
        database = FirebaseDatabase.getInstance();
        favoriteList = database.getReference("Post");

        //Load menu
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_favorites);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

//        loadMenu();

        return view;
    }


}
