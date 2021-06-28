package com.example.coffeetime.ui.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_profile:
                return  true;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}