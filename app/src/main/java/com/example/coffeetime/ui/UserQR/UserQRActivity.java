package com.example.coffeetime.ui.UserQR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.coffeetime.common.Functions.closeDrawer;
import static com.example.coffeetime.common.Functions.logout;
import static com.example.coffeetime.common.Functions.openDrawer;
import static com.example.coffeetime.common.Functions.redirectActivity;
import static com.example.coffeetime.state.InitialState.ownerUser;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.product.ProductAdminActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;
import com.squareup.picasso.Picasso;

public class UserQRActivity extends AppCompatActivity {

    ImageView qr, profileQr;
    TextView nameQr;
    DrawerLayout drawerLayout;
    ImageView d_photo;
    TextView tv_id, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_qr_activity);
        qr = findViewById(R.id.qr_);
        profileQr = findViewById(R.id.profileQR_);
        nameQr = findViewById(R.id.txtNameQr_);
        drawerLayout = findViewById(R.id.drawer_layout);
        d_photo = findViewById(R.id.drawerPhoto);
        tv_id = findViewById(R.id.drawerId);
        tv_email = findViewById(R.id.drawerEmail);
        tv_id.setText(ownerUser.getName());
        tv_email.setText(ownerUser.getEmail());
        Picasso.get().load(ownerUser.getPhotoUri()).into(d_photo);
        addImage();
    }

    public void addImage() {
        nameQr.setText(ownerUser.getName() + " " + ownerUser.getLastName());
        Picasso.get().load(ownerUser.getQrUser()).into(qr);
        Picasso.get().load(ownerUser.getPhotoUri()).into(profileQr);
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }


    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }


    public void ClickHome(View view) {
        redirectActivity(this, HomeActivity.class);
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