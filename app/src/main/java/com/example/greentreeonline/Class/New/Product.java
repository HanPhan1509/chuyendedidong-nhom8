package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Comparator;

@IgnoreExtraProperties
public class Product implements Serializable {
    private String idsp;
    private String tensp;
    private int gia;
    private String mota;
    private String imgsp;
    private String iduser;
    private String idcat;
    private String idcatchild;
    private String thuonghieu;
    private String xuatxu;
    private int status;

    public Product() {

    }

    public Product(String idsp, String tensp, int gia, String mota, String imgsp, String iduser, String idcat, String idcatchild, String thuonghieu, String xuatxu, int status) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.gia = gia;
        this.mota = mota;
        this.imgsp = imgsp;
        this.iduser = iduser;
        this.idcat = idcat;
        this.idcatchild = idcatchild;
        this.thuonghieu = thuonghieu;
        this.xuatxu = xuatxu;
        this.status = status;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getImgsp() {
        return imgsp;
    }

    public void setImgsp(String imgsp) {
        this.imgsp = imgsp;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

    public String getIdcatchild() {
        return idcatchild;
    }

    public void setIdcatchild(String idcatchild) {
        this.idcatchild = idcatchild;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static Comparator<Product> priceComparator = new Comparator<Product>() {
        @Override
        public int compare(Product jc1, Product jc2) {
            return (jc2.getGia() > jc1.getGia() ? -1 :
                    (jc2.getGia() == jc1.getGia() ? 0 : 1));
        }
    };

    public static Comparator<Product> priceDesComparator = new Comparator<Product>() {
        @Override
        public int compare(Product jc1, Product jc2) {
            return (jc2.getGia() < jc1.getGia() ? -1 :
                    (jc2.getGia() == jc1.getGia() ? 0 : 1));
        }
    };

}
