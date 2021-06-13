package com.example.greentreeonline.Class;

import java.io.Serializable;

public class StatusBill implements Serializable {
    private int idct, idtk, iddh, masp, giasp, sl, sdt, tt, tamtinh, phiship;
    private String tensp, tenkh, diachigh, ngay, imgsp,trangthai;

    public StatusBill(int idct, int idtk, int iddh, int masp, int giasp, int sl, int sdt, int tt, int tamtinh, int phiship, int trangthai, String tensp, String tenkh, String diachigh, String ngay, String imgsp) {

    }

    public StatusBill(int idtk, int madh, String hoten, int sdt, String diachi, String date, int phiship, int tamtinh, int sotien, String trangthai) {

        this.idtk = idtk;
        this.iddh = madh;
        this.sdt = sdt;
        this.tt = sotien;
        this.tamtinh = tamtinh;
        this.phiship = phiship;
        this.trangthai = trangthai;

        this.tenkh = hoten;
        this.diachigh = diachi;
        this.ngay = date;


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

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
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