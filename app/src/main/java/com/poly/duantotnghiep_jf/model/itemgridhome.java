package com.poly.duantotnghiep_jf.model;

public class itemgridhome {
    private int img;
    private String ten;

    public itemgridhome(int img, String ten) {
        this.img = img;
        this.ten = ten;
    }

    public itemgridhome() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
