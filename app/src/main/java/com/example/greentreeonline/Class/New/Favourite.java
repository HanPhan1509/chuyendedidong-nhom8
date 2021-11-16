package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Favourite {
    private String iduser;
    private String idsp;
    private String tensp;
    private int gia;
    private String imgsp;

    public Favourite(){

    }

    public Favourite(String iduser, String idsp, String tensp, int gia, String imgsp) {
        this.iduser = iduser;
        this.idsp = idsp;
        this.tensp = tensp;
        this.gia = gia;
        this.imgsp = imgsp;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getImgsp() {
        return imgsp;
    }

    public void setImgsp(String imgsp) {
        this.imgsp = imgsp;
    }
}
