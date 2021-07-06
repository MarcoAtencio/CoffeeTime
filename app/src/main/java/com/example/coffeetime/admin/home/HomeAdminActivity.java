package com.example.coffeetime.admin.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.product.ProductAdminActivity;
import com.example.coffeetime.admin.sales.SalesActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.ui.home.HomeActivity;

import static com.example.coffeetime.common.Functions.redirectActivity;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                return true;

            case R.id.menu_product:
                redirectActivity(this, ProductAdminActivity.class);
                break;

            case R.id.menu_user:
                redirectActivity(this, UserAdminActivity.class);
                break;

            case R.id.menu_sales:
                redirectActivity(this, SalesActivity.class);
                break;

            case R.id.menu_logout:
                redirectActivity(this, SignInActivity.class);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}
