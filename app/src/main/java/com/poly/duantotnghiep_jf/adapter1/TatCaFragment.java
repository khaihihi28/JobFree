package com.poly.duantotnghiep_jf.adapter1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.interfaceall.Ban_Interface;
import com.example.sanphamdemo.server.Ban_User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TatCaFragment extends Fragment {
    private GridView gridView;
    private AdapterItem adapter;
    private List<Ban_User> dataList = new ArrayList<>();

    public TatCaFragment() {
        // Required empty public constructor
    }

    public static TatCaFragment newInstance() {
        TatCaFragment fragment = new TatCaFragment();
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
        return inflater.inflate(R.layout.fragment_tat_ca, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.id_fragment_GridView);
        adapter = new AdapterItem(getContext(), dataList);
        gridView.setAdapter(adapter);

        if (getActivity() == null) {
            getActivity().recreate();
        }

fetchData();

    }
    private void fetchData() {
        // Tạo Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/") // Điền địa chỉ cơ sở của API của bạn
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Tạo instance của ApiService từ Retrofit
        Ban_Interface apiService = retrofit.create(Ban_Interface.class);

        // Gọi API để lấy dữ liệu
        Call<List<Ban_User>> call = apiService.getListProduct1();
        call.enqueue(new Callback<List<Ban_User>>() {
            @Override
            public void onResponse(Call<List<Ban_User>> call, Response<List<Ban_User>> response) {
                // Xử lý dữ liệu khi nhận được
                List<Ban_User> responseData = response.body();

                // Cập nhật dataList và thông báo thay đổi trong Adapter
                dataList.addAll(responseData);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Ban_User>> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }
    private void restartFragment() {
        // Tạo một FragmentTransaction để thay thế hoặc thêm lại Fragment
        FragmentTransaction fragmentTransaction = requireFragmentManager().beginTransaction();

        // Thay thế hoặc thêm lại Fragment của bạn
        fragmentTransaction.replace(R.id.id_fragment_GridView, new TatCaFragment());

        // Hoặc sử dụng addToBackStack để thêm Fragment vào back stack
        // fragmentTransaction.addToBackStack(null);

        // Kết thúc giao dịch và xác nhận
        fragmentTransaction.commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getContext(), "khoi dong lai", Toast.LENGTH_SHORT).show();
        // Thực hiện các hành động cụ thể bạn muốn khi Activity chứa Fragment bị hủy
        // Ví dụ: khởi động lại Fragment
        restartFragment();
    }



}
