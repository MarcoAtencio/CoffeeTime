package com.example.coffeetime.logic;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.model.Product;

import java.util.ArrayList;

public class LCart {

    public static ArrayList<Product> cart = new ArrayList<Product>();
    Context context;
    RecyclerView recyclerView;
    TextView tv_subTotal,tv_igv,tv_total;

    public LCart(){

    }

    public LCart(Context context_, RecyclerView recyclerView_,TextView tv_subTotal_, TextView tv_igv_, TextView tv_total_){
        context = context_;
        recyclerView = recyclerView_;
        tv_subTotal = tv_subTotal_;
        tv_igv = tv_igv_;
        tv_total = tv_total_;
        showDataToRecyclerView();
    }


    public double subTotal(){
        double subTotal_= 0;
        for (int i=0; i<cart.size();i++){
            subTotal_+=Double.parseDouble( cart.get(i).getPrice())*cart.get(i).getCantProductCart();
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
        ProductCartAdapter productCartAdapter = new ProductCartAdapter(cart, context,tv_subTotal,tv_igv,tv_total);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productCartAdapter);
    }
}
