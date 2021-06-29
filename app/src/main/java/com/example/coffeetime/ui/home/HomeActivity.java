package com.example.coffeetime.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.logic.LProduct;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LProduct lProducts;
    Integer categoryProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.listProduct_);
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

       /*
    StarterPlate =1
    dish = 2
    drinks = 3
    desserts = 4
     */
    public void ShowStarterPlate(View view){
        Toast.makeText(HomeActivity.this,"StarterPlate",Toast.LENGTH_SHORT).show();
        categoryProduct = 1;
        lProducts= new LProduct(HomeActivity.this, recyclerView,categoryProduct);
    }

    public void ShowDish(View view){
        Toast.makeText(HomeActivity.this,"Dish",Toast.LENGTH_SHORT).show();
        categoryProduct = 2;
        lProducts= new LProduct(HomeActivity.this, recyclerView,categoryProduct);
    }

    public void ShowDrinks(View view){
        Toast.makeText(this,"Drinks",Toast.LENGTH_SHORT).show();
        categoryProduct = 3;
        lProducts= new LProduct(HomeActivity.this, recyclerView,categoryProduct);
    }

    public void ShowDesserts(View view){
        Toast.makeText(this,"Desserts",Toast.LENGTH_SHORT).show();
        categoryProduct = 4;
        lProducts= new LProduct(HomeActivity.this, recyclerView,categoryProduct);
    }

}
