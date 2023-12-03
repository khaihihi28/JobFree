package com.poly.duantotnghiep_jf.Model;

import java.util.List;

public class Company {
    private String name;
    private String uidAccount;
    private String address;
    private String avatar;
    private String linhVuc;
    private String phucLoi;
    private List<String> listAnh;
    private boolean pheDuyet;
    private int followCount;

    public Company() {
    }

    public Company(String name, String uidAccount, String address, String linhVuc) {
        this.name = name;
        this.uidAccount = uidAccount;
        this.address = address;
        this.linhVuc = linhVuc;
        phucLoi = "";
        listAnh = null;
        pheDuyet = false;
        avatar = "R.drawable.avatar_company_default";
        followCount = 0;
    }

    public String getUidAccount() {
        return uidAccount;
    }

    public void setUidAccount(String uidAccount) {
        this.uidAccount = uidAccount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(String linhVuc) {
        this.linhVuc = linhVuc;
    }

    public String getPhucLoi() {
        return phucLoi;
    }

    public void setPhucLoi(String phucLoi) {
        this.phucLoi = phucLoi;
    }

    public List<String> getListAnh() {
        return listAnh;
    }

    public void setListAnh(List<String> listAnh) {
        this.listAnh = listAnh;
    }

    public boolean isPheDuyet() {
        return pheDuyet;
    }

    public void setPheDuyet(boolean pheDuyet) {
        this.pheDuyet = pheDuyet;
    }
}
