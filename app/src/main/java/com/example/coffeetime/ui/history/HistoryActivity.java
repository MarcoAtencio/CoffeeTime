package com.example.coffeetime.ui.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.logic.LSale;
import com.example.coffeetime.ui.UserQR.UserQRActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;
import com.squareup.picasso.Picasso;

import static com.example.coffeetime.common.Functions.closeDrawer;
import static com.example.coffeetime.common.Functions.logout;
import static com.example.coffeetime.common.Functions.openDrawer;
import static com.example.coffeetime.common.Functions.redirectActivity;
import static com.example.coffeetime.state.InitialState.ownerUser;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView rv_listOwnPurchase;
    public LSale lSale;
    DrawerLayout drawerLayout;
    ImageView d_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rv_listOwnPurchase = findViewById(R.id.listhistory);
        drawerLayout = findViewById(R.id.drawer_layout);
        d_photo = findViewById(R.id.drawerPhoto);
        Picasso.get().load(ownerUser.getPhotoUri()).into(d_photo);
        lSale = new LSale(HistoryActivity.this, rv_listOwnPurchase);
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
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_history:
                return true;

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

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        redirectActivity(this, UserQRActivity.class);
    }


    public void ClickHome(View view) {
        redirectActivity(this, HomeActivity.class);
    }

    public void ClickCart(View view) {
        redirectActivity(this, CartActivity.class);
    }


    public void ClickHistory(View view) {
        closeDrawer(drawerLayout);
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