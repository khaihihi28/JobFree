package com.poly.duantotnghiep_jf.test_chuc_nang_yeu_thich;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.R;

import java.util.ArrayList;
import java.util.List;

public class List_test extends AppCompatActivity {
    Button button2;

    RecyclerView recyclerView;
    AdapterRec adapterRec;
    List<User_test> mlistuser = new ArrayList<>();;
    ArrayList<User_test> list;
    Adapter_test adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);


    ////////////////    initUi();

        button2 = (Button) findViewById(R.id.button2);
        recyclerView = findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        adapterRec = new AdapterRec(mlistuser);


    //////////////////    getListDataFirebase();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ListUser");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    User_test userTest  = dataSnapshot.getValue(User_test.class);
                    mlistuser.add(userTest);
                }
adapterRec.notifyDataSetChanged();
                Toast.makeText(List_test.this, "Get List User Thanh Cong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(List_test.this, "Get List User Faild", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.setAdapter(adapterRec);
    }

    private void initUi() {



    }

    public void getListDataFirebase() {

    }
}