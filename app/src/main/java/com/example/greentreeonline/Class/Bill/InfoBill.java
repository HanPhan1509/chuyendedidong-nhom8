package com.example.greentreeonline.Class.Bill;

public class InfoBill {
    int idchitiet,idhd,masp,giasp,sluong,phiship,tamtinh;

String tensp,imgsp;

    public InfoBill(int idchitiet, int idhd, int masp, int giasp, int sluong, int phiship, int tamtinh, String tensp, String imgsp) {
        this.idchitiet = idchitiet;
        this.idhd = idhd;
        this.masp = masp;
        this.giasp = giasp;
        this.sluong = sluong;
        this.phiship = phiship;
        this.tamtinh = tamtinh;
        this.tensp = tensp;
        this.imgsp = imgsp;
    }

    public InfoBill(int idct, int id, int masp, String tensp, int gia, String igmsp, int sl) {
        this.idchitiet = idct;
        this.idhd = id;
        this.masp = masp;
        this.giasp = gia;
        this.sluong = sl;
        this.tensp = tensp;
        this.imgsp = igmsp;
    }


    public int getIdchitiet() {
        return idchitiet;
    }

    public void setIdchitiet(int idchitiet) {
        this.idchitiet = idchitiet;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
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

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public int getPhiship() {
        return phiship;
    }

    public void setPhiship(int phiship) {
        this.phiship = phiship;
    }

    public int getTamtinh() {
        return tamtinh;
    }

    public void setTamtinh(int tamtinh) {
        this.tamtinh = tamtinh;
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
}
