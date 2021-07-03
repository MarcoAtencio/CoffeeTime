package com.example.coffeetime.ui.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.logic.LSale;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LSale lSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //lSale = new LSale( HistoryActivity.this, recyclerView);
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
                return true;

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