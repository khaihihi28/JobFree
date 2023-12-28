package com.poly.duantotnghiep_jf.adapter1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.user.UserMonAn;

import java.util.ArrayList;

public class AFragment extends Fragment {

    public AFragment() {
        // Required empty public constructor
    }

    public static DoAnFragment newInstance() {
        DoAnFragment fragment = new DoAnFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_do_an, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        ListView lvdoan = (ListView) view.findViewById(R.id.lvdoan);



        AdapterMonan myAdapter = new AdapterMonan(getContext(),getListData1(), R.layout.item_thucdon);

        lvdoan.setAdapter(myAdapter);
    }

    public ArrayList<UserMonAn> getListData1(){
        ArrayList<UserMonAn> list = new ArrayList<>();

        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));   list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));   list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));   list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));   list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));
        list.add(new UserMonAn("Thịt Xiên", "9999","1"));


        return list;
    }
}