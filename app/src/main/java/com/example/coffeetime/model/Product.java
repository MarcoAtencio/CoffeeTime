package com.example.coffeetime.model;

import java.io.Serializable;

public class Product  implements Serializable {
    String uid;
    String name;
    String price;
    String category;
    String photoURI;
    String stock;
    Integer cantProductCart;

    public Product() {
        this.uid = "";
        this.name = "";
        this.price = "";
        this.category = "";
        this.photoURI = "";
        this.stock = "";
        this.cantProductCart = 0;
    }

    public Product(String uid, String name, String price, String category, String photoURI, String stock) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.category = category;
        this.photoURI = photoURI;
        this.stock = stock;
        this.cantProductCart = 0;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getCantProductCart() { return cantProductCart; }

    public void setCantProductCart(Integer cantProductCart) { this.cantProductCart = cantProductCart; }

    public void increaseCantProductCart(){
        if (Integer.parseInt(this.stock) > this.cantProductCart){
            this.cantProductCart++;
        }
    }

    public void decreaseCantProductCart(){
        if (this.cantProductCart>0){
            this.cantProductCart--;
        }
    }
}
