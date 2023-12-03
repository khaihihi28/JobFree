package com.poly.duantotnghiep_jf.Model;

import com.poly.duantotnghiep_jf.Util.DateTimeUtils;

public class Post {
    private String title;
    private int soLuong;
    private String address;
    private String thoiGianLamViec;
    private String bonus;
    private String moTa;
    private int luong;
    private String yeuCau;
    private String idCompany;
    private String timestamp;
    private int likeCount;
    private int viewCount;
    private int ungTuyenCount;


    public Post() {
    }

    public Post(String title, int soLuong, String address, String thoiGianLamViec, String bonus, String moTa, int luong, String yeuCau, String idCompany) {
        this.title = title;
        this.soLuong = soLuong;
        this.address = address;
        this.thoiGianLamViec = thoiGianLamViec;
        this.bonus = bonus;
        this.moTa = moTa;
        this.luong = luong;
        this.yeuCau = yeuCau;
        this.idCompany = idCompany;
        timestamp = DateTimeUtils.getCurrentDateTimeString();
        viewCount = 0;
        likeCount = 0;
        ungTuyenCount = 0;
    }

    public int getUngTuyenCount() {
        return ungTuyenCount;
    }

    public void setUngTuyenCount(int ungTuyenCount) {
        this.ungTuyenCount = ungTuyenCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThoiGianLamViec() {
        return thoiGianLamViec;
    }

    public void setThoiGianLamViec(String thoiGianLamViec) {
        this.thoiGianLamViec = thoiGianLamViec;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
