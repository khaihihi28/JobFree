package com.poly.duantotnghiep_jf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.model.itemgridhome;

import java.util.ArrayList;

public class Gridhome extends RecyclerView.Adapter<Gridhome.Viewholder>{
    Context mContext;
    ArrayList<itemgridhome> list = new ArrayList<>();

    public Gridhome(Context mContext, ArrayList<itemgridhome> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.itemgrid, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        itemgridhome itemgridhome = list.get(position);
        holder.img.setImageResource(itemgridhome.getImg());
        holder.txt.setText(itemgridhome.getTen());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}
