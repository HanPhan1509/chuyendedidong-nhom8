package com.example.greentreeonline.Class.Bill;

import java.io.Serializable;

public class Bill implements Serializable {
    private int idct,idtk, iddh, masp, giasp, sl, sdt, tt, tamtinh, phiship;
    private String tensp, tenkh, diachigh, ngay, imgsp,trangthai;


    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Bill(int idtk, int madh, String hoten, int sdt, String diachi, String date, int phiship, int tamtinh, int sotien, String trangthai) {
        this.idtk = idtk;
        this.iddh = madh;
        this.tenkh = hoten;
        this.diachigh = diachi;
        this.sdt=sdt;
        this.ngay = date;
        this.tamtinh= tamtinh;
        this.phiship=phiship;
        this.tt = sotien;
        this.trangthai = trangthai;
    }

    public int getIdct() {
        return idct;
    }

    public void setIdct(int idct) {
        this.idct = idct;
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public int getIddh() {
        return iddh;
    }

    public void setIddh(int iddh) {
        this.iddh = iddh;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public int getTamtinh() {
        return tamtinh;
    }

    public void setTamtinh(int tamtinh) {
        this.tamtinh = tamtinh;
    }

    public int getPhiship() {
        return phiship;
    }

    public void setPhiship(int phiship) {
        this.phiship = phiship;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachigh() {
        return diachigh;
    }

    public void setDiachigh(String diachigh) {
        this.diachigh = diachigh;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getImgsp() {
        return imgsp;
    }

    public void setImgsp(String imgsp) {
        this.imgsp = imgsp;
    }
}