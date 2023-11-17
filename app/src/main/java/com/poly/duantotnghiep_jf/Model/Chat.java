package com.poly.duantotnghiep_jf.Model;

public class Chat {
    private int ID;
    private int IDAccount1;
    private int IDAccount2;

    public Chat() {
    }

    public Chat(int ID, int IDAccount1, int IDAccount2) {
        this.ID = ID;
        this.IDAccount1 = IDAccount1;
        this.IDAccount2 = IDAccount2;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDAccount1() {
        return IDAccount1;
    }

    public void setIDAccount1(int IDAccount1) {
        this.IDAccount1 = IDAccount1;
    }

    public int getIDAccount2() {
        return IDAccount2;
    }

    public void setIDAccount2(int IDAccount2) {
        this.IDAccount2 = IDAccount2;
    }
}
