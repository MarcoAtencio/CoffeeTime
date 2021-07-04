package com.example.coffeetime.logic;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LProduct {

    public static ArrayList<Product> listProduct = new ArrayList<Product>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context context;
    RecyclerView recyclerView;
    String categorySelectProduct;


    public LProduct(Context context_, RecyclerView recyclerView_, String categorySelectProduct_) {
        context = context_;
        recyclerView = recyclerView_;
        categorySelectProduct = categorySelectProduct_;
        initFirebase(context);
        showDataToRecyclerView(categorySelectProduct);

    }

    private void initFirebase(Context context) {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public ArrayList<Product> getAllProduct() {
        return listProduct;
    }

    public ArrayList<Product> getProductToStarterPlate(String category) {
        return filterProductForCategory(category);
    }

    public ArrayList<Product> getProductToDish(String category) {
        return filterProductForCategory(category);
    }

    public ArrayList<Product> getProductToDrink(String category) {
        return filterProductForCategory(category);
    }

    public ArrayList<Product> getProductToDessert(String category) {
        return filterProductForCategory(category);
    }

    public ArrayList<Product> filterProductForCategory(String category) {
        ArrayList<Product> filterListForCategory = new ArrayList<Product>();
        for (int i = 0; i < listProduct.size(); i++) {
            Product product = listProduct.get(i);
            if (product.getCategory().equals(category)) {
                filterListForCategory.add(product);
            }
        }
        return filterListForCategory;
    }


    private void showDataToRecyclerView(String categoryProduct) {
        ProductAdapter productAdapter;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        productAdapter = new ProductAdapter(getProductToStarterPlate(categoryProduct), context);
        recyclerView.setAdapter(productAdapter);

    }


}
