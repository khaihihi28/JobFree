package com.poly.duantotnghiep_jf.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poly.duantotnghiep_jf.Adapter.Gridhome;
import com.poly.duantotnghiep_jf.Adapter.Recytwo;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.model.itemgridhome;
import com.poly.duantotnghiep_jf.model.itemthu2;

import java.util.ArrayList;

public class Home1 extends Fragment {
    RecyclerView recy, recy1,recy2;
    ArrayList<itemgridhome> list = new ArrayList<>();
    Gridhome gridhome;
    ArrayList<itemthu2> list1 = new ArrayList<>();
    Recytwo adapter;

    public Home1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home1, container, false);
        recy = view.findViewById(R.id.recy);
        recy1 = view.findViewById(R.id.recy1);
        recy2 = view.findViewById(R.id.recy2);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recy.setLayoutManager(layoutManager);
        list.add(new itemgridhome(R.drawable.logoconen, "Job Free"));
        list.add(new itemgridhome(R.drawable.logo_fpt, "FPT Polytechnic"));
        gridhome = new Gridhome(getActivity(), list);
        recy.setAdapter(gridhome);
        loadrecy1();
        loadrecy2();
        return view;
    }
    public void loadrecy1(){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy1.setLayoutManager(manager);
        list1.add(new itemthu2("Data Engineer - Salary Up to $3000", "Lên đến $3,000", "Địa chỉ tổng quát"));
        list1.add(new itemthu2("Tên Công việc", "Lương", "Địa chỉ tổng quát"));
        adapter = new Recytwo(getActivity(), list1);
        recy1.setAdapter(adapter);
    }
    public void loadrecy2(){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy2.setLayoutManager(manager);
        list1.add(new itemthu2("Data Engineer - Salary Up to $3000", "Lên đến $3,000", "Địa chỉ tổng quát"));
        list1.add(new itemthu2("Tên Công việc", "Lương", "Địa chỉ tổng quát"));
        adapter = new Recytwo(getActivity(), list1);
        recy2.setAdapter(adapter);
    }
}