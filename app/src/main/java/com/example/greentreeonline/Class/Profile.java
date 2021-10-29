package com.example.greentreeonline.Class;

import java.io.Serializable;

public class Profile implements Serializable {
    private int id, sdt;
    private String taikhoan, hoten, gmail, diachi, gioitinh;
    private String ngaysinh,imgtk;


       public Profile(int id, String tenTK, String hoten, String gioitinh, int sdt, String gmail, String date, String diachi, String img) {
        this.id = id;
        this.sdt = sdt;
        this.taikhoan = tenTK;
        this.hoten = hoten;
        this.gmail = gmail;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.ngaysinh =date;
        this.imgtk = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getImgtk() {
        return imgtk;
    }

    public void setImgtk(String imgtk) {
        this.imgtk = imgtk;
    }
}