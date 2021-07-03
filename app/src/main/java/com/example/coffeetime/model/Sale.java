package com.example.coffeetime.model;

import java.util.ArrayList;

public class Sale {
    String uid;
    ArrayList<Product> listProduct;
    String user;
    boolean state;
    String amountTotal;

    public Sale() {
    }

    public Sale(String uid, ArrayList<Product> listProduct, String amountTotal, boolean state ,String user) {
        this.uid = uid;
        this.user = user;
        this.state = state;
        this.listProduct = listProduct;
        this.amountTotal = amountTotal;
    }

    public String getUid() {
        return uid;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public String getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(String amountTotal) {
        this.amountTotal = amountTotal;
    }
}
