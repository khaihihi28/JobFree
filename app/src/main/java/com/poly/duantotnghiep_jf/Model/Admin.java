package com.poly.duantotnghiep_jf.Model;

public class Admin {
    private int ID;
    private String email;
    private String ten;
    private String ho;
    private String password;
    private String avatar;

    public Admin() {
    }

    public Admin(int ID, String email, String ten, String ho, String password, String avatar) {
        this.ID = ID;
        this.email = email;
        this.ten = ten;
        this.ho = ho;
        this.password = password;
        this.avatar = avatar;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
