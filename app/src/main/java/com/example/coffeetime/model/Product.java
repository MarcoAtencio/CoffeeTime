package com.example.coffeetime.model;

import java.io.Serializable;

public class   Product  implements Serializable {
    String uid;
    String name;
    double price;
    String category;
    String photoURI;
    int stock;
    Integer cantProductCart = 0;

    public Product() {
        this.uid = "";
        this.name = "";
        this.price = 0.0;
        this.category = "";
        this.photoURI = "";
        this.stock = 0;
    }

    public Product(String uid, String name, double price, String category, String photoURI, int stock) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.category = category;
        this.photoURI = photoURI;
        this.stock = stock;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer cantProduct() { return cantProductCart; }



    public void increaseCantProductCart(){
        if (this.stock > this.cantProductCart){
            this.cantProductCart++;
        }
    }

    public void decreaseCantProductCart(){
        if (this.cantProductCart>0){
            this.cantProductCart--;
        }
    }
}
