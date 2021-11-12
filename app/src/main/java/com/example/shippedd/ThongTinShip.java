package com.example.shippedd;


public class ThongTinShip {
    private String email;
    private String name;
    private String password;
    private String socancuoc;
    private String id;

    public ThongTinShip() {
    }

    public ThongTinShip(String email, String name, String password, String socancuoc, String id) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.socancuoc = socancuoc;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocancuoc() {
        return socancuoc;
    }

    public void setSocancuoc(String socancuoc) {
        this.socancuoc = socancuoc;
    }


}