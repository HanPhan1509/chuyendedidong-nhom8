package com.example.greentreeonline.Class;

public class Favourite {
    int idtk;
    int idsp;
    String tensp;
    int Gt;
    String imgsp;
    String mota;

    public Favourite(int idtk, int idsp, String tensp, int gt, String imgsp, String mota) {
        this.idtk = idtk;
        this.idsp = idsp;
        this.tensp = tensp;
        Gt = gt;
        this.imgsp = imgsp;
        this.mota = mota;
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
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