package com.poly.duantotnghiep_jf.Model;

public class OrderJCoin {
    private String accountId;
    private int coinChange;
    private String noiDung;
    private Object timestamp;

    public OrderJCoin() {
    }

    public OrderJCoin(String accountId, int coinChange, String noiDung, Object timestamp) {
        this.accountId = accountId;
        this.coinChange = coinChange;
        this.noiDung = noiDung;
        this.timestamp = timestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getCoinChange() {
        return coinChange;
    }

    public void setCoinChange(int coinChange) {
        this.coinChange = coinChange;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
