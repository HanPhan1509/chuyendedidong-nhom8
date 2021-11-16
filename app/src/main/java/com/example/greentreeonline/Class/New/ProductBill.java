package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ProductBill {
    private String idbill;
    private String idsp;
    private String tensp;
    private String imgsp;
    private int gia;
    private int sl;
    private String idshop;
    private int status;
    private String namestatus;

    public ProductBill(){

    }

    public ProductBill(String idbill, String idsp, String tensp, String imgsp, int gia, int sl, String idshop, int status, String namestatus) {
        this.idbill = idbill;
        this.idsp = idsp;
        this.tensp = tensp;
        this.imgsp = imgsp;
        this.gia = gia;
        this.sl = sl;
        this.idshop = idshop;
        this.status = status;
        this.namestatus = namestatus;
    }

    public String getIdbill() {
        return idbill;
    }

    public void setIdbill(String idbill) {
        this.idbill = idbill;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
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

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNamestatus() {
        return namestatus;
    }

    public void setNamestatus(String namestatus) {
        this.namestatus = namestatus;
    }
}
