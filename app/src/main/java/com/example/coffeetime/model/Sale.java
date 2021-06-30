package com.example.coffeetime.model;

import java.util.ArrayList;

public class Sale {
    String uid;
    ArrayList<Product> listProduct;
    String amountTotal;

    public Sale() {
    }

    public Sale(String uid, ArrayList<Product> listProduct, String amountTotal) {
        this.uid = uid;
        this.listProduct = listProduct;
        this.amountTotal = amountTotal;
    }

    public String getUid() {
        return uid;
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
