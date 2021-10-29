package com.example.greentreeonline.Class;

public class Cart {
    private int idgh;
    private String tensp;
    private String imgsp;
    private int Gia;
    private int sl;

    public int getIdgh() {
        return idgh;
    }

    public void setIdgh(int idgh) {
        this.idgh = idgh;
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public Cart(int idgh, String tensp, String imgsp, int gia, int sl) {
        this.idgh = idgh;
        this.tensp = tensp;
        this.imgsp = imgsp;
        Gia = gia;
        this.sl = sl;
    }

    public Cart(){

    }

    public int tongTien(){
        return Gia*sl;
    }
}
