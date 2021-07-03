package com.example.coffeetime.logic;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.Sale;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.UUID;

import static com.example.coffeetime.logic.LCart.cart;
import static com.example.coffeetime.state.InitialState.ownerUser;

public class LSale {

    Context context;
    public static ArrayList<Sale> listOwnPurchase = new ArrayList<Sale>();
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    Sale sale;
    LCart lCart;
    public LSale(){

    }

    public LSale(Context context_, RecyclerView recyclerView_){
        this.context = context_;
        this.lCart = new LCart();
        this.sale = new Sale();
        this.recyclerView = recyclerView_;
        firebaseFirestore = FirebaseFirestore.getInstance();
        showDataToRecyclerView();

    }

    public void registerSale(){
        sale.setUid(UUID.randomUUID().toString());
        sale.setAmountTotal(""+ lCart.Total());
        sale.setUser(ownerUser.getEmail());
        sale.setState(false);
        sale.setListProduct(cart);
        firebaseFirestore.collection("Sale").document(sale.getUid()).set(sale);
        cart.clear();
        Toast.makeText(context, "Venta Exitosa", Toast.LENGTH_SHORT).show();
    }

    private void showDataToRecyclerView() {
        SaleAdapter saleAdapter = new SaleAdapter( listOwnPurchase ,context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(saleAdapter);
    }
}
