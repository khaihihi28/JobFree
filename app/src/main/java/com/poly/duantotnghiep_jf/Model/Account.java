package com.poly.duantotnghiep_jf.Model;

import java.io.Serializable;

public class Account implements Serializable {
    private String avatar;
    private String email;
    private String name;
    private String phone;
    private boolean isNewAccount;
    private String chuyenNganh;
    private String kinhNghiem;
    private String luong;
    private String timeWork;
    private String trinhDo;
    private boolean manegeCompany;
    private long coin;
    private boolean rule;

    public Account() {
    }

    public Account(String avatar, String email, String name, String phone, boolean isNewAccount) {
        this.avatar = avatar;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.isNewAccount = isNewAccount;
        rule = false;
        chuyenNganh = "";
        kinhNghiem = "";
        luong = "";
        timeWork = "";
        trinhDo = "";
        manegeCompany = false;
        coin = 0;
    }

    public long getCoin() {
        return coin;
    }

    public void setCoin(long coin) {
        this.coin = coin;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public boolean isManegeCompany() {
        return manegeCompany;
    }

    public void setManegeCompany(boolean manegeCompany) {
        this.manegeCompany = manegeCompany;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isNewAccount() {
        return isNewAccount;
    }

    public void setNewAccount(boolean newAccount) {
        isNewAccount = newAccount;
    }
}
