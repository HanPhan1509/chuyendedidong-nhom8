package com.example.greentreeonline.Class.User;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String id;
    private String hoten;
    private String sdt;
    private String gmail;
    private String diachi;
    private String gioitinh;
    private String ngaysinh;
    private String password;
    private String imgtk;

    public User(){

    }

    public User(User user){
        this.id = user.getId();
        this.hoten = user.getHoten();
        this.sdt = user.getSdt();
        this.gmail = user.getGmail();
        this.diachi = user.getDiachi();
        this.gioitinh = user.getGioitinh();
        this.ngaysinh = user.getNgaysinh();
        this.password = user.getPassword();
        this.imgtk = user.getImgtk();
    }

    public User(String id, String hoten, String sdt, String gmail, String diachi, String gioitinh, String ngaysinh, String password, String imgtk) {
        this.id = id;
        this.hoten = hoten;
        this.sdt = sdt;
        this.gmail = gmail;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.password = password;
        this.imgtk = imgtk;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgtk() {
        return imgtk;
    }

    public void setImgtk(String imgtk) {
        this.imgtk = imgtk;
    }
}
