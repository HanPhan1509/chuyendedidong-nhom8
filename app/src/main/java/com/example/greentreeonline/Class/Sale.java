package com.example.greentreeonline.Class;

import java.io.Serializable;

public class Sale implements Serializable {
    int idspsale;
    String tenspsale;
    int Gtsale;

    int sale;
    String imgspsale;
    String mota;

    public Sale(int idspsale, String tenspsale, int gtsale, int sale, String imgspsale, String mota) {
        this.idspsale = idspsale;
        this.tenspsale = tenspsale;
        Gtsale = gtsale;
        this.sale = sale;
        this.imgspsale = imgspsale;
        this.mota = mota;
    }

    public int getIdspsale() {
        return idspsale;
    }

    public void setIdspsale(int idspsale) {
        this.idspsale = idspsale;
    }

    public String getTenspsale() {
        return tenspsale;
    }

    public void setTenspsale(String tenspsale) {
        this.tenspsale = tenspsale;
    }

    public int getGtsale() {
        return Gtsale;
    }

    public void setGtsale(int gtsale) {
        Gtsale = gtsale;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getImgspsale() {
        return imgspsale;
    }

    public void setImgspsale(String imgspsale) {
        this.imgspsale = imgspsale;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }


    public int sale(){
        return Gtsale-(Gtsale*sale)/100;
    }
}