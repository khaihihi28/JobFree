package com.poly.duantotnghiep_jf.Model;

public class Congty {
    private int ID;
    private String tenCongTy;
    private String diaChi;
    private byte[] avatar;
    private String linhVuc;
    private String phucLoi;
    private String hinhAnh;
    private boolean pheDuyet;

    public Congty() {
    }

    public Congty(int ID, String tenCongTy, String diaChi, byte[] avatar, String linhVuc, String phucLoi, String hinhAnh, boolean pheDuyet) {
        this.ID = ID;
        this.tenCongTy = tenCongTy;
        this.diaChi = diaChi;
        this.avatar = avatar;
        this.linhVuc = linhVuc;
        this.phucLoi = phucLoi;
        this.hinhAnh = hinhAnh;
        this.pheDuyet = pheDuyet;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isPheDuyet() {
        return pheDuyet;
    }

    public void setPheDuyet(boolean pheDuyet) {
        this.pheDuyet = pheDuyet;
    }
}
