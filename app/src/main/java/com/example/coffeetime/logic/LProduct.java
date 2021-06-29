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

    static ArrayList<Product> listProduct = new ArrayList<Product>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context context;
    RecyclerView recyclerView;


    public LProduct (Context context_, RecyclerView recyclerView_){
        context = context_;
        recyclerView = recyclerView_;
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

    public ArrayList<Product> getProduct(){
        return  listProduct;
    }

    private void showDataToRecyclerView() {
        ProductAdapter productAdapter = new ProductAdapter(listProduct, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productAdapter);
    }


}
