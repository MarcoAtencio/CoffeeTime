package com.example.coffeetime.logic;

import android.content.Context;
import android.text.format.DateFormat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coffeetime.model.Sale;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static com.example.coffeetime.logic.LCart.cart;
import static com.example.coffeetime.state.InitialState.ownerUser;

public class LSale {

    Context context;
    public static ArrayList<Sale> listOwnPurchase = new ArrayList<Sale>();
    public static ArrayList<Sale> listSale = new ArrayList<Sale>();

    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    Sale sale = new Sale();
    LCart lCart = new LCart();



    public LSale() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LSale(Context context_, RecyclerView recyclerView_) {
        this.context = context_;
        this.recyclerView = recyclerView_;
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    public void registerSale() {

        sale.setUid(UUID.randomUUID().toString());
        sale.setDateSale(new Date());
        sale.setAmountTotal(lCart.Total());
        sale.setUser(ownerUser.getEmail());
        sale.setState(false);
        sale.setListProduct(cart);
        firebaseFirestore.collection("Sale").document(sale.getUid()).set(sale);
        listOwnPurchase.add(sale);
    }

    public void showDataToRecyclerView() {
        SaleAdapter saleAdapter = new SaleAdapter(listOwnPurchase, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(saleAdapter);
    }

    public void showSaleToRecyclerView() {
        SaleAdapter saleAdapter = new SaleAdapter(listSale, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(saleAdapter);
    }


}
