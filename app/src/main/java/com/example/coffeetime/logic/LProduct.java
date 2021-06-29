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

    public final String STARTER_PLATE ="1";
    public final String  DISH = "2";
    public final String  DRINK = "3";
    public final String  DESSERT = "4";
    public static ArrayList<Product> listProduct = new ArrayList<Product>();
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
        showDataToRecyclerView();

    }

    private void initFirebase(Context context){
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public ArrayList<Product> getAllProduct(){
        return  listProduct;
    }

    public ArrayList<Product> getProductToStarterPlate(){ return filterProductForCategory(STARTER_PLATE); }

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


}
