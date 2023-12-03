package com.poly.duantotnghiep_jf.Model;

public class Favourite {
    private String idPost;
    private String uid;

    public Favourite() {
    }

    public Favourite(String idPost, String uid) {
        this.idPost = idPost;
        this.uid = uid;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
