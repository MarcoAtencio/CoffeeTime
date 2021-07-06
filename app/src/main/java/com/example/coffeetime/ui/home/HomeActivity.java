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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.logic.LProduct;
import com.example.coffeetime.ui.UserQR.UserQRActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;
import com.squareup.picasso.Picasso;

import static com.example.coffeetime.common.Constants.DESSERT;
import static com.example.coffeetime.common.Constants.DISH;
import static com.example.coffeetime.common.Constants.DRINK;
import static com.example.coffeetime.common.Constants.STARTER_PLATE;
import static com.example.coffeetime.common.Functions.closeDrawer;
import static com.example.coffeetime.common.Functions.logout;
import static com.example.coffeetime.common.Functions.openDrawer;
import static com.example.coffeetime.common.Functions.redirectActivity;
import static com.example.coffeetime.state.InitialState.ownerUser;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_listProduct;
    LProduct lProducts;
    DrawerLayout drawerLayout;
    TextView tv_id, tv_email;
    ImageView d_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_listProduct = findViewById(R.id.listProduct_);
        drawerLayout = findViewById(R.id.drawer_layout);
        tv_id = findViewById(R.id.drawerId);
        tv_email = findViewById(R.id.drawerEmail);
        d_photo = findViewById(R.id.drawerPhoto);

        tv_id.setText(ownerUser.getName());
        tv_email.setText(ownerUser.getEmail());
        Picasso.get().load(ownerUser.getPhotoUri()).into(d_photo);
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
