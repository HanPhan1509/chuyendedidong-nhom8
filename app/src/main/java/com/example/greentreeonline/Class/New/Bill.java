package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Bill {
    private String id;
    private String iduser;
    private String tenkh;
    private String dt;
    private String diachi;
    private String date;
    private int tamtinh;
    private int phiship;
    private int tongtien;
    private int status;
    private String namestatus;
    private String idshop;

    public Bill(){

    }

    public Bill(String id, String idUser, String idshop,String tenkh, String dt, String diachi, String date, int tamtinh, int phiship, int tongtien, int status, String namestatus) {
        this.id = id;
        this.iduser = idUser;
        this.tenkh = tenkh;
        this.dt = dt;
        this.diachi = diachi;
        this.date = date;
        this.idshop = idshop;
        this.tamtinh = tamtinh;
        this.phiship = phiship;
        this.tongtien = tongtien;
        this.status = status;
        this.namestatus = namestatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String idUser) {
        this.iduser = idUser;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
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

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }
}
