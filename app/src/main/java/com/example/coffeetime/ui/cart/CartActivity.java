package com.example.coffeetime.ui.cart;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.auth.SignUpActivity;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.ui.UserQR.UserQRActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.pay.PayActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;
import com.squareup.picasso.Picasso;

import static com.example.coffeetime.common.Functions.closeDrawer;
import static com.example.coffeetime.common.Functions.logout;
import static com.example.coffeetime.common.Functions.openDrawer;
import static com.example.coffeetime.common.Functions.redirectActivity;
import static com.example.coffeetime.state.InitialState.ownerUser;

public class CartActivity extends AppCompatActivity {

    TextView tv_subTotal, tv_igv, tv_total, tv_message;
    Button button;
    LCart lCart;
    RecyclerView rv_listCart;
    DrawerLayout drawerLayout;
    ImageView d_photo;
    TextView tv_id, tv_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rv_listCart = findViewById(R.id.listProduct);
        tv_igv = findViewById(R.id.montoIGV);
        tv_subTotal = findViewById(R.id.precioSubtotal);
        tv_total = findViewById(R.id.montoTotal);
        tv_message = findViewById(R.id.txtMessage);
        button = findViewById(R.id.btn_Start);
        drawerLayout = findViewById(R.id.drawer_layout);
        d_photo = findViewById(R.id.drawerPhoto);
        tv_id = findViewById(R.id.drawerId);
        tv_email = findViewById(R.id.drawerEmail);
        tv_id.setText(ownerUser.getName());
        tv_email.setText(ownerUser.getEmail());
        Picasso.get().load(ownerUser.getPhotoUri()).into(d_photo);
        lCart = new LCart(this, rv_listCart, new TextView[]{tv_subTotal, tv_igv, tv_total});

        if (!LCart.cart.isEmpty()) {
            tv_message.setVisibility(View.GONE);
        } else {
            tv_message.setVisibility(View.VISIBLE);
        }
    }

    public void payCart(View view) {
        if (!LCart.cart.isEmpty()) {
            redirectActivity(this, PayActivity.class);
        } else {
            Toast.makeText(this, "Agregar productos al carrito", Toast.LENGTH_SHORT).show();
        }
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
       closeDrawer(drawerLayout);
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