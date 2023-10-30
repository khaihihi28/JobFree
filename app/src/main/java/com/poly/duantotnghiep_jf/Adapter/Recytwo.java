package com.poly.duantotnghiep_jf.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.duantotnghiep_jf.Activity.Chitietcongviec;
import com.poly.duantotnghiep_jf.R;
import com.poly.duantotnghiep_jf.model.itemthu2;

import java.util.ArrayList;

public class Recytwo extends RecyclerView.Adapter<Recytwo.Viewholder>{
    Context mContext;
    ArrayList<itemthu2> list = new ArrayList<>();

    public Recytwo(Context mContext, ArrayList<itemthu2> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.itemdanhsach, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        itemthu2 itemthu2 = list.get(position);
        holder.txt1.setText(itemthu2.getText1());
        holder.txt2.setText(itemthu2.getText2());
        holder.txt3.setText(itemthu2.getText3());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);
        }
        public void Viewholder(@NonNull Viewholder holder, int position){
            //Job job = Job.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Chitietcongviec.class);
                    //intent.putExtra("JOB_ID", job.getId());
                    //mContext.startActivities(intent);
                }
            });
        }
    }
}
