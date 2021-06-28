package com.example.coffeetime.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coffeetime.R;
import com.example.coffeetime.ui.home.HomeActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void Home(View view){
        Intent intent = new Intent( this, HomeActivity.class);
        startActivity(intent);
    }

}
