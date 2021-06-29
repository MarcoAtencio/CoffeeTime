package com.example.coffeetime.admin.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.home.HomeAdminActivity;
import com.example.coffeetime.admin.product.ProductAdminActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;

public class SalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){

            case R.id.menu_home:
                intent = new Intent(this, HomeAdminActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_product:
                intent = new Intent(this, ProductAdminActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_user:
                intent = new Intent(this, UserAdminActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_sales:
                return true;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}