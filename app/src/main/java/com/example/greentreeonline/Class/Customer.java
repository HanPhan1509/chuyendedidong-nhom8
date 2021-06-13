package com.example.greentreeonline.Class;

import java.io.Serializable;

public class Customer implements Serializable {
    private int idtk,idkh;
    private String hoten;
    private int sdt;
    private String diachi;

    public Customer(int idtk, int idkh, String hoten, int sdt, String diachi) {
        this.idtk = idtk;
        this.idkh = idkh;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return getHoten()+""+getSdt()+""+getDiachi();
    }

}
