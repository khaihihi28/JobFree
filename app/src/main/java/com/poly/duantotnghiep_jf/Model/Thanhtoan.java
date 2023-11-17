package com.poly.duantotnghiep_jf.Model;

public class Thanhtoan {
    private int ID;
    private String soTien;
    private String thoiGian;
    private String phuongThucThanhToan;

    public Thanhtoan() {
    }

    public Thanhtoan(int ID, String soTien, String thoiGian, String phuongThucThanhToan) {
        this.ID = ID;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
}
