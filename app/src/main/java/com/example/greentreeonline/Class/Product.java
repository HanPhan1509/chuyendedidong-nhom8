package com.example.greentreeonline.Class;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Product implements Serializable {
    String idsp;
    String tensp;
    int gia;
    String imgsp;
    String mota;
    String idcat;

    public Product(){

    }
    public Product(String idsp, String tensp, int gia, String imgsp, String mota) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.gia = gia;
        this.imgsp = imgsp;
        this.mota = mota;
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
        gia = gia;
    }

    public String getImgsp() {
        return imgsp;
    }

    public void setImgsp(String imgsp) {
        this.imgsp = imgsp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}

