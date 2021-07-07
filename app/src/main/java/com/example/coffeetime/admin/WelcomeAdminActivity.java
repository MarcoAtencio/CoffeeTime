package com.example.coffeetime.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.home.HomeAdminActivity;
import com.example.coffeetime.state.InitialState;
import com.example.coffeetime.ui.home.HomeActivity;

public class WelcomeAdminActivity extends AppCompatActivity {

    InitialState initialState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin);
        initialState = new InitialState(this);
        initialState.stateListSale();
    }

    public void Home(View view){
        Intent intent = new Intent( this, HomeAdminActivity.class);
        startActivity(intent);
    }

}