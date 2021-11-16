package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DoanhThu {
    private int thang;
    private int doanhthu;
    private String idshop;

    public DoanhThu(int thang, int doanhthu, String idshop) {
        this.thang = thang;
        this.doanhthu = doanhthu;
        this.idshop = idshop;
    }

    public DoanhThu(){

    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }
}
