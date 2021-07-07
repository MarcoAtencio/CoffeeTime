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
    TextView tv_id, tv_email;
    ImageView d_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rv_listOwnPurchase = findViewById(R.id.listhistory);
        drawerLayout = findViewById(R.id.drawer_layout);
        d_photo = findViewById(R.id.drawerPhoto);
        tv_id = findViewById(R.id.drawerId);
        tv_email = findViewById(R.id.drawerEmail);
        tv_id.setText(ownerUser.getName());
        tv_email.setText(ownerUser.getEmail());
        Picasso.get().load(ownerUser.getPhotoUri()).into(d_photo);
        lSale = new LSale(HistoryActivity.this, rv_listOwnPurchase);
        lSale.showDataToRecyclerView();
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