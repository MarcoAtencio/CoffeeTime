package com.example.coffeetime.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.model.User;
import com.example.coffeetime.state.InitialState;
import com.example.coffeetime.ui.home.HomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomeActivity extends AppCompatActivity {
    InitialState initialState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initialState = new InitialState(this);
        initialState.stateListProducts();
        initialState.stateUser(getIntent().getStringExtra("email"));
        initialState.stateListOwnPurchase(getIntent().getStringExtra("email"));

    }

    public void Home(View view){
        Intent intent = new Intent( this, HomeActivity.class);
        startActivity(intent);
    }


}
