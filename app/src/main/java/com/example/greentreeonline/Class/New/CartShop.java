package com.example.greentreeonline.Class.New;

import java.util.ArrayList;

public class CartShop {
    private String idshop;
    private ArrayList<Cart> carts;

    public CartShop(){

    }

    public CartShop(String idshop, ArrayList<Cart> carts) {
        this.idshop = idshop;
        this.carts = carts;
    }

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }
}
