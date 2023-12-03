package com.poly.duantotnghiep_jf.Model;

public class Account {
    private String avatar;
    private String email;
    private String name;
    private String ho;
    private String phone;
    private boolean isNewAccount;
    private String chuyenNganh;
    private String kinhNghiem;
    private String luong;
    private String timeWork;
    private String trinhDo;
    private boolean manegeCompany;

    public Account() {
    }

    public Account(String avatar, String email, String name, String ho, String phone, boolean isNewAccount) {
        this.avatar = avatar;
        this.email = email;
        this.name = name;
        this.ho = ho;
        this.phone = phone;
        this.isNewAccount = isNewAccount;
        chuyenNganh = "";
        kinhNghiem = "";
        luong = "";
        timeWork = "";
        trinhDo = "";
        manegeCompany = false;
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

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
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
