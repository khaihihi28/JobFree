package com.poly.duantotnghiep_jf.Interface;

import com.example.sanphamdemo.server.Ban_User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface A_Interface {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    Ban_Interface apiselectpeoduct1 = new Retrofit.Builder().baseUrl("http://192.168.1.2:3000/").addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(Ban_Interface.class);

    @GET("ban")
    Call<List<Ban_User>> getListProduct1();
}
