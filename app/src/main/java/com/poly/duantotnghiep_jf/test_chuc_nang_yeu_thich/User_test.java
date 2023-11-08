package com.poly.duantotnghiep_jf.test_chuc_nang_yeu_thich;

import java.io.Serializable;

public class User_test implements Serializable {
    String manv;
    String hoten;
    String luong;

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public User_test(String manv, String hoten, String luong) {
        this.manv = manv;
        this.hoten = hoten;
        this.luong = luong;
    }
}
