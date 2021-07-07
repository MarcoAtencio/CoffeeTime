package com.example.coffeetime.model;

import java.util.ArrayList;
import java.util.Date;




import static com.example.coffeetime.common.Constants.STATE_PENDING;

public class Sale {
    String uid;
    ArrayList<Product> listProduct;
    String user;
    Date dateSale;
    boolean state;
    double amountTotal;

    public Sale() {
        this.uid = "";
        this.user = "";
        this.state = STATE_PENDING;
        this.listProduct = new ArrayList<Product>();
        this.amountTotal = 0.0;

    }

    public Sale(String uid, ArrayList<Product> listProduct, double amountTotal, boolean state ,String user, Date dateSale) {
        this.uid = uid;
        this.user = user;
        this.state = state;
        this.listProduct = listProduct;
        this.amountTotal = amountTotal;
        this.dateSale = dateSale;
    }

    public String getUid() {
        return uid;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
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

    public double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(double amountTotal) {
        this.amountTotal = amountTotal;
    }
}
