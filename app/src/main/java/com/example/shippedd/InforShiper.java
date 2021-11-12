package com.example.shippedd;

public class InforShiper {
    private String id;
    private String hoten;
    private int sodienthoai;
    private int socancuoc;

    public InforShiper(String id, String hoten, int sodienthoai, int socancuoc) {
        this.id = id;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.socancuoc = socancuoc;
    }

    public InforShiper() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public int getSocancuoc() {
        return socancuoc;
    }

    public void setSocancuoc(int socancuoc) {
        this.socancuoc = socancuoc;
    }
}
