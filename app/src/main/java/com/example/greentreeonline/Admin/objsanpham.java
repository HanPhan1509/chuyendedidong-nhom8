package com.example.greentreeonline.Admin;

import java.io.Serializable;

public class objsanpham implements Serializable {
    int idsp;
    String tensp;
    int Gt;
    String imgsp;
    String mota;

    public objsanpham(int id, String tensp, int gia, String igmsp, String mota) {
        this.idsp = id;
        this.tensp = tensp;
        Gt = gia;
        this.imgsp = igmsp;
        this.mota = mota;

    }


    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGt() {
        return Gt;
    }

    public void setGt(int gt) {
        Gt = gt;
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

