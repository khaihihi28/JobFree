package com.poly.duantotnghiep_jf.Model;

public class Danhsachnganh {
    private int ID;
    private String tenNganh;

    public Danhsachnganh() {
    }

    public Danhsachnganh(int ID, String tenNganh) {
        this.ID = ID;
        this.tenNganh = tenNganh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
}
