package com.example.greentreeonline.Class.User;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Staff extends User{
    private String idOwner;

    public Staff(String idOwner) {
        this.idOwner = idOwner;
    }

    public Staff(String id, String hoten, String sdt, String gmail, String diachi, String gioitinh, String ngaysinh, String password, String imgtk, String idOwner) {
        super(id, hoten, sdt, gmail, diachi, gioitinh, ngaysinh, password, imgtk);
        this.idOwner = idOwner;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }
}
