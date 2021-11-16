package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Rate {
    private String idsp;
    private String iduser;
    private String nameuser;
    private String danhgia;
    private float star;

    public Rate(String idsp, String iduser, String nameuser, String danhgia, float star) {
        this.idsp = idsp;
        this.iduser = iduser;
        this.nameuser = nameuser;
        this.danhgia = danhgia;
        this.star = star;
    }
    public Rate() {

    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
