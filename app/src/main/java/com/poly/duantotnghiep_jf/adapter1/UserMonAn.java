package com.poly.duantotnghiep_jf.adapter1;

public class UserMonAn {
    private String tenmonan;
    private String giamon;
    private String tangsl,soluongmon,giamsl;

    public UserMonAn(String tenmonan, String giamon, String soluongmon) {
        this.tenmonan = tenmonan;
        this.giamon = giamon;

        this.soluongmon = soluongmon;

    }

    public UserMonAn() {
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public String getGiamon() {
        return giamon;
    }

    public void setGiamon(String giamon) {
        this.giamon = giamon;
    }

    public String getTangsl() {
        return tangsl;
    }

    public void setTangsl(String tangsl) {
        this.tangsl = tangsl;
    }

    public String getSoluongmon() {
        return soluongmon;
    }

    public void setSoluongmon(String soluongmon) {
        this.soluongmon = soluongmon;
    }

    public String getGiamsl() {
        return giamsl;
    }

    public void setGiamsl(String giamsl) {
        this.giamsl = giamsl;
    }
}
