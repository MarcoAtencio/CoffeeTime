package com.example.coffeetime.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart {

    public static ArrayList<Product> cart = new ArrayList<Product>();

    public void addProduct(Product product){
        cart.add(product);
    }

    public int countProducts(){
        return cart.size();
    }


    public void showToast(Context context){
        for (int i=0; i<countProducts();i++){
            Toast.makeText(context.getApplicationContext(), "SubTotal"+subTotal(), Toast.LENGTH_SHORT).show();
        }
    }

    public double subTotal(){
        double subTotal_=0;
        for (int i=0; i<countProducts();i++){
            subTotal_+=Double.parseDouble( cart.get(i).getPrice());
        }
        return subTotal_;
    }
}
