package com.example.coffeetime.admin.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.coffeetime.logic.LSale;
import com.example.coffeetime.model.Sale;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

import static com.example.coffeetime.common.Functions.redirectActivity;

public class SalesActivity extends AppCompatActivity {

    RecyclerView rv_listSale;
    LSale lSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        rv_listSale = findViewById(R.id.listhistory);
        lSale = new LSale(this,rv_listSale);
        lSale.showSaleToRecyclerView();


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
                redirectActivity(this, HomeAdminActivity.class);
                break;

            case R.id.menu_product:
                redirectActivity(this, ProductAdminActivity.class);
                break;

            case R.id.menu_user:
                redirectActivity(this, UserAdminActivity.class);
                break;

            case R.id.menu_sales:
                return true;

            case R.id.menu_logout:
                redirectActivity(this, SignInActivity.class);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}