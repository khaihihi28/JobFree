package com.poly.duantotnghiep_jf.test_chuc_nang_yeu_thich;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import com.poly.duantotnghiep_jf.R;

import java.util.ArrayList;

public class Adapter_test extends BaseAdapter {
Context context;
ArrayList<User_test>list;
ActivityResultLauncher<Intent> getdata;
    public Adapter_test(Context context, ArrayList<User_test> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater =((Activity)context).getLayoutInflater();
        view = layoutInflater.inflate(R
                .layout.item_test,viewGroup,false);


        EditText   editTextText = (EditText) view.findViewById(R.id.editTextText);
        EditText  editTextText2 = (EditText) view.findViewById(R.id.editTextText2);
        EditText   editTextText3 = (EditText) view.findViewById(R.id.editTextText3);

        Button  button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          button4.setText("đã yêu thích ");
          button4.setTextColor(R.color.teal_200);


          button4.setBackgroundColor(R.color.purple_700);
                Toast.makeText(context, "add list", Toast.LENGTH_SHORT).show();

            }
        });

          editTextText.setText(list.get(i).getManv());
        editTextText2.setText(list.get(i).getHoten());
        editTextText3.setText(list.get(i).getLuong());
        return view;
    }


}
