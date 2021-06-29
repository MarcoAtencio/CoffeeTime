package com.example.coffeetime.logic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LProduct {

    public static final String STARTER_PLATE ="1";
    public static final String  DISH = "2";
    public static final String  DRINK = "3";
    public static final String  DESSERT = "4";
    static ArrayList<Product> listProduct = new ArrayList<Product>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context context;
    RecyclerView recyclerView;
    Integer categorySelectProduct;


    public LProduct (Context context_, RecyclerView recyclerView_,Integer categorySelectProduct_){
        context = context_;
        recyclerView = recyclerView_;
        categorySelectProduct= categorySelectProduct_;
        initFirebase(context);
        setDataToListProducts();
    }

    private void initFirebase(Context context){
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void setDataToListProducts(){
        databaseReference.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                listProduct.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    listProduct.add(product);
                }
                showDataToRecyclerView();
            }
            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
            }
        });
    }

    public ArrayList<Product> getAllProduct(){
        return  listProduct;
    }

    private void showDataToRecyclerView() {
        ProductAdapter productAdapter;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        switch (categorySelectProduct){
            case 1:
                productAdapter = new ProductAdapter(getProductToStarterPlate(), context);
                break;
            case 2:
                productAdapter = new ProductAdapter(getProductToDish(), context);
                break;
            case 3:
                productAdapter = new ProductAdapter(getProductToDrink(), context);
                break;
            case 4:
                productAdapter = new ProductAdapter(getProductToDessert(), context);
                break;

            default:
                productAdapter = new ProductAdapter(listProduct, context);
                break;
        }
        recyclerView.setAdapter(productAdapter);

    }


    public ArrayList<Product> getProductToStarterPlate(){
        return filterProductForCategory(STARTER_PLATE);
    }

    public ArrayList<Product> getProductToDish(){
        return filterProductForCategory(DISH);
    }

    public ArrayList<Product> getProductToDrink(){
        return filterProductForCategory(DRINK);
    }

    public ArrayList<Product> getProductToDessert(){
        return filterProductForCategory(DESSERT);
    }


    public ArrayList<Product> filterProductForCategory(String category){
        ArrayList<Product> filterListForCategory = new  ArrayList<Product>();
        for (int i=0; i< listProduct.size();i++){
            Product product = listProduct.get(i);
            if (product.getCategory().equals(category)){
                filterListForCategory.add(product);
            }
        }
        return filterListForCategory;
    }

    public void cleanListProducts(){
        listProduct.clear();
    }
}
