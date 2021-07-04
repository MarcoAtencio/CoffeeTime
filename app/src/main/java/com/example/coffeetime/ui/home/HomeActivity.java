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

import static com.example.coffeetime.common.Constants.DESSERT;
import static com.example.coffeetime.common.Constants.DISH;
import static com.example.coffeetime.common.Constants.DRINK;
import static com.example.coffeetime.common.Constants.STARTER_PLATE;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_listProduct;
    LProduct lProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_listProduct = findViewById(R.id.listProduct_);
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

    public void showStarterPlate(View view){
        lProducts= new LProduct(HomeActivity.this, rv_listProduct,STARTER_PLATE);
    }

    public void showDish(View view){
        lProducts= new LProduct(HomeActivity.this, rv_listProduct,DISH);
    }

    public void showDrinks(View view){
        lProducts= new LProduct(HomeActivity.this, rv_listProduct,DRINK);
    }

    public void showDesserts(View view){
        lProducts= new LProduct(HomeActivity.this, rv_listProduct,DESSERT);
    }

}
