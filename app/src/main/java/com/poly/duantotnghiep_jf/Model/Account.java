package com.poly.duantotnghiep_jf.Model;

public class Account {
    private String email;
    private String name;
    private String ho;
    private String phone;
    private boolean isNewAccount;

    public Account() {
    }

    public Account(String email, String name, String ho, String phone, boolean isNewAccount) {
        this.email = email;
        this.name = name;
        this.ho = ho;
        this.phone = phone;
        this.isNewAccount = isNewAccount;
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
