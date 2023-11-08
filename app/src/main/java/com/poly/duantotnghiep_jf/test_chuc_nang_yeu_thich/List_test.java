package com.poly.duantotnghiep_jf.test_chuc_nang_yeu_thich;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.poly.duantotnghiep_jf.R;

import java.util.ArrayList;

public class List_test extends AppCompatActivity {
     Button button2;
     ListView listlv;


    ArrayList<User_test> list = new ArrayList<>();
    Adapter_test adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        button2 = (Button) findViewById(R.id.button2);
        listlv = (ListView) findViewById(R.id.list);
        list.add(new User_test("1","thinh","999"));
        list.add(new User_test("2","thinh2","999"));
        adapter = new Adapter_test(this,list);
        listlv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}