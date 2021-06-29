package com.example.coffeetime.ui.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;

public class CartActivity extends AppCompatActivity {

    TextView tv_subTotal,tv_igv,tv_total;
    Button button;
    LCart lCart;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.listProduct);
        lCart = new LCart(this,recyclerView);
        tv_igv = findViewById(R.id.montoIGV);
        tv_subTotal = findViewById(R.id.precioSubtotal);
        tv_total =findViewById(R.id.montoTotal);
        button = findViewById(R.id.btn_Start);
        tv_subTotal.setText("S/"+ lCart.subTotal());
        tv_igv.setText("S/"+ lCart.igv());
        tv_total.setText("S/"+ lCart.Total());
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

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}