package com.example.shippedd;

public class Bill {
    private  String date;
    private  String diachi;
    private  String dt;
    private  String id;
    private  String idshop;
    private  String iduser;
    private  String namestatus;
    private  int phiship;
    private  int status;
    private  int tamtinh;
    private  String tenkh;
    private  int tongtien;

    public Bill() {
    }

    public Bill(String date, String diachi, String dt, String id, String idshop, String iduser, String namestatus, int phiship, int status, int tamtinh, String tenkh, int tongtien) {
        this.date = date;
        this.diachi = diachi;
        this.dt = dt;
        this.id = id;
        this.idshop = idshop;
        this.iduser = iduser;
        this.namestatus = namestatus;
        this.phiship = phiship;
        this.status = status;
        this.tamtinh = tamtinh;
        this.tenkh = tenkh;
        this.tongtien = tongtien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNamestatus() {
        return namestatus;
    }

    public void setNamestatus(String namestatus) {
        this.namestatus = namestatus;
    }

    public int getPhiship() {
        return phiship;
    }

    public void setPhiship(int phiship) {
        this.phiship = phiship;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTamtinh() {
        return tamtinh;
    }

    public void setTamtinh(int tamtinh) {
        this.tamtinh = tamtinh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
