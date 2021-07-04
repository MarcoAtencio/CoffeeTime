package com.example.coffeetime.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.coffeetime.ui.UserQR.UserQRActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;

import static com.example.coffeetime.common.Constants.DESSERT;
import static com.example.coffeetime.common.Constants.DISH;
import static com.example.coffeetime.common.Constants.DRINK;
import static com.example.coffeetime.common.Constants.STARTER_PLATE;
import static com.example.coffeetime.common.Functions.closeDrawer;
import static com.example.coffeetime.common.Functions.logout;
import static com.example.coffeetime.common.Functions.openDrawer;
import static com.example.coffeetime.common.Functions.redirectActivity;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_listProduct;
    LProduct lProducts;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_listProduct = findViewById(R.id.listProduct_);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
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
            case R.id.menu_QR:
                intent = new Intent(this, UserQRActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showStarterPlate(View view) {
        lProducts = new LProduct(HomeActivity.this, rv_listProduct, STARTER_PLATE);
    }

    public void showDish(View view) {
        lProducts = new LProduct(HomeActivity.this, rv_listProduct, DISH);
    }

    public void showDrinks(View view) {
        lProducts = new LProduct(HomeActivity.this, rv_listProduct, DRINK);
    }

    public void showDesserts(View view) {
        lProducts = new LProduct(HomeActivity.this, rv_listProduct, DESSERT);
    }


    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }



    public void ClickLogo(View view) {
        redirectActivity(this, UserQRActivity.class);
    }


    public void ClickHome(View view) {
        closeDrawer(drawerLayout);
    }

    public void ClickCart(View view) {
        redirectActivity(this, CartActivity.class);
    }


    public void ClickHistory(View view) {
        redirectActivity(this, HistoryActivity.class);
    }

    public void ClickProfile(View view) {
        redirectActivity(this, ProfileActivity.class);
    }

    public void ClickExit(View view) {
        logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void ClickLogout(View view) {
        redirectActivity(this, SignInActivity.class);
    }

}
