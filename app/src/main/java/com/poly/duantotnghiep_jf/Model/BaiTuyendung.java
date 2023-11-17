package com.poly.duantotnghiep_jf.Model;

public class BaiTuyendung {
    private int ID;
    private String tieuDe;
    private int soLuong;
    private String thoiGian;
    private String uuDai;
    private String moTa;
    private int luong;
    private String yeuCau;

    public BaiTuyendung() {
    }

    public BaiTuyendung(int ID, String tieuDe, int soLuong, String thoiGian, String uuDai, String moTa, int luong, String yeuCau) {
        this.ID = ID;
        this.tieuDe = tieuDe;
        this.soLuong = soLuong;
        this.thoiGian = thoiGian;
        this.uuDai = uuDai;
        this.moTa = moTa;
        this.luong = luong;
        this.yeuCau = yeuCau;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getUuDai() {
        return uuDai;
    }

    public void setUuDai(String uuDai) {
        this.uuDai = uuDai;
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
}
