package com.example.coffeetime.ui.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.ui.UserQR.UserQRActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.pay.PayActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;

public class CartActivity extends AppCompatActivity {

    TextView tv_subTotal, tv_igv, tv_total;
    Button button;
    LCart lCart;
    RecyclerView rv_listCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rv_listCart = findViewById(R.id.listProduct);
        tv_igv = findViewById(R.id.montoIGV);
        tv_subTotal = findViewById(R.id.precioSubtotal);
        tv_total = findViewById(R.id.montoTotal);
        button = findViewById(R.id.btn_Start);
        lCart = new LCart(this, rv_listCart, new TextView[]{tv_subTotal, tv_igv, tv_total});
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
                return true;

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

    public void payCart(View view) {
        if (!LCart.cart.isEmpty()) {
            Intent intent = new Intent(this, PayActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Agregar productos al carrito", Toast.LENGTH_SHORT).show();
        }
    }
}