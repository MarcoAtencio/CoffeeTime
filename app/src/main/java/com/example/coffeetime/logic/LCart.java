package com.example.coffeetime.logic;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.model.Product;

import java.util.ArrayList;

public class LCart {

    public static ArrayList<Product> cart = new ArrayList<Product>();
    Context context;
    RecyclerView recyclerView;

    public LCart(){

    }

    public LCart(Context context_, RecyclerView recyclerView_){
        context = context_;
        recyclerView = recyclerView_;
        showDataToRecyclerView();
    }

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
        double subTotal_= 0;
        for (int i=0; i<countProducts();i++){
            subTotal_+=Double.parseDouble( cart.get(i).getPrice());
        }
        return subTotal_;
    }


    public  double igv(){
        return  subTotal()* 0.18;
    }

    public double Total(){
        return subTotal() + igv();
    }

    private void showDataToRecyclerView() {
        ProductAdapter productAdapter = new ProductAdapter(cart, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productAdapter);
    }
}
