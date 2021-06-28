package com.example.coffeetime.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.product.ProductActivity;
import com.example.coffeetime.admin.sales.SalesActivity;
import com.example.coffeetime.admin.user.UserActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.model.Cart;
import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.ProductAdapter;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Activity activity;
    RecyclerView recyclerView;
    EditText txtnombre;
    View view;
    public List<Product> listProduct = new ArrayList<Product>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFirebase();
        listProduct();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_home:
             return true;

            case R.id.menu_cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;


        }
        return  super.onOptionsItemSelected(item);
    }


    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void listProduct(){
        databaseReference.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                listProduct.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    listProduct.add(product);
                    Toast.makeText(HomeActivity.this,product.getName(),Toast.LENGTH_SHORT).show();
                }


            }
            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(HomeActivity.this,"Aviso De Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarData() {
        ProductAdapter productAdapter = new ProductAdapter(listProduct, this);
        RecyclerView recyclerView = view.findViewById(R.id.listProduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
    }
}
