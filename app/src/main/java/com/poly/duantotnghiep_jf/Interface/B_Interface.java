package com.poly.duantotnghiep_jf.Interface;

import com.example.sanphamdemo.DeleteBan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface B_Interface {
    @FormUrlEncoded
    @POST("ban/del/:id")
    Call<DeleteBan> DelListProduct1(@Field("id")int id);
}
