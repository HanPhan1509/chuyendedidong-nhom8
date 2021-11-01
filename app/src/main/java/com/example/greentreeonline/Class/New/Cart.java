package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cart {
    private String iduser;
    private String idsp;
    private String tensp;
    private String imgsp;
    private int gia;
    private int sl;
    private String idshop;

    public Cart() {

    }

    public Cart(String iduser, String idsp, String tensp, String imgsp, int gia, int sl, String idshop) {
        this.iduser = iduser;
        this.idsp = idsp;
        this.tensp = tensp;
        this.imgsp = imgsp;
        this.gia = gia;
        this.sl = sl;
        this.idshop = idshop;
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

    public String getImgsp() {
        return imgsp;
    }

    public void setImgsp(String imgsp) {
        this.imgsp = imgsp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int tongTien(){
        return sl * gia;
    }

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }
}
