package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Customer implements Serializable {
    private String id;
    private String iduser;
    private String hoten;
    private String sdt;
    private String diachi;

    public Customer(){

    }

    public Customer(String id, String iduser, String hoten, String sdt, String diachi) {
        this.id = id;
        this.iduser = iduser;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
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

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
