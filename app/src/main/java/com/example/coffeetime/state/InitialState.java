package com.example.coffeetime.state;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.coffeetime.logic.LProduct;
import com.example.coffeetime.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.coffeetime.logic.LProduct.listProduct;

public class InitialState {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context context;

    public InitialState(Context context_){
        context = context_;
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
            }
            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
            }
        });
    }
}
