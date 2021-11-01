package com.example.greentreeonline.Class.New;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Staff implements Serializable {
    private String idnv;
    private String tennv;
    private String emailnv;
    private String sdtnv;
    private String dcnv;
    private String idowner;

    public Staff(){

    }

    public Staff(String idnv, String tennv, String emailnv, String sdtnv, String dcnv, String idowner) {
        this.idnv = idnv;
        this.tennv = tennv;
        this.emailnv = emailnv;
        this.sdtnv = sdtnv;
        this.dcnv = dcnv;
        this.idowner = idowner;
    }

    public String getIdnv() {
        return idnv;
    }

    public void setIdnv(String idnv) {
        this.idnv = idnv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getEmailnv() {
        return emailnv;
    }

    public void setEmailnv(String emailnv) {
        this.emailnv = emailnv;
    }

    public String getSdtnv() {
        return sdtnv;
    }

    public void setSdtnv(String sdtnv) {
        this.sdtnv = sdtnv;
    }

    public String getDcnv() {
        return dcnv;
    }

    public void setDcnv(String dcnv) {
        this.dcnv = dcnv;
    }

    public String getIdowner() {
        return idowner;
    }

    public void setIdowner(String idowner) {
        this.idowner = idowner;
    }
}
