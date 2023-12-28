package com.poly.duantotnghiep_jf.adapter1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.activity.ThucDonBan;
import com.example.sanphamdemo.server.Ban_User;

import java.util.List;

public class AdapterItem extends BaseAdapter {
    private List<Ban_User> dataList;
    private LayoutInflater inflater;

  Context context;
    Ban_User data;
    public AdapterItem(Context context, List<Ban_User> dataList) {
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public AdapterItem() {
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        ///////////
        if (view == null) {
            view = inflater.inflate(R.layout.itemhomefragment, null);
            holder = new ViewHolder();
            holder.detail_item = view.findViewById(R.id.detail_item);
            holder.tenbann = view.findViewById(R.id.tenban);
            holder.gioo = view.findViewById(R.id.gio);
            holder.giatien = view.findViewById(R.id.gia);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
         data = dataList.get(i);
        holder.tenbann.setText(data.getTen());
        holder.giatien.setText(data.getGia()+" Đ");
        holder.gioo.setText(data.getGiovao());
        if (data.getTrangthai() == 1){
            holder.tenbann.setTextColor(Color.parseColor("#00FF00"));


        }
        holder.detail_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThucDonBan.class);
                Bundle bundle = new Bundle();
                 String ten  =dataList.get(i).getTen();
                 int idxoa = dataList.get(i).getId();
                bundle.putString("ten", ten);
                bundle.putString("idxoa", String.valueOf(idxoa));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
        ////////


    }

    public static class ViewHolder  {

              LinearLayout detail_item;
         TextView tenbann, gioo, giatien;
    }
}
