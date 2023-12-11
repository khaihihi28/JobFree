package com.poly.duantotnghiep_jf.Model;

public class CombinedData {
    private Company company;
    private Post post;

    public CombinedData() {
    }

    public CombinedData(Company company, Post post) {
        this.company = company;
        this.post = post;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Company getCompany() {
        return company;
    }

    public Post getPost() {
        return post;
    }

}
