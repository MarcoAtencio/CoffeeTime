package com.example.coffeetime.ui.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.logic.LSale;
import com.example.coffeetime.model.Sale;
import com.example.coffeetime.ui.home.HomeActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.coffeetime.logic.LCart.cart;

public class PayActivity extends AppCompatActivity {

    TextView tv_totalPay, tv_cantPay;
    LSale lSale;
    LCart lcart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        lSale = new LSale();
        lcart = new LCart();
        tv_totalPay = findViewById(R.id.pricePay);
        tv_cantPay = findViewById(R.id.titlePay);
        tv_cantPay.setText(cart.size() + " " + "Articulos en tu carrito");
        tv_totalPay.setText("S/. " + lcart.Total());

    }

    public void saveSale(View view){
        lSale.registerSale();
        Toast.makeText(this, "Gracias por su compra", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent( this, HomeActivity.class);
        startActivity(intent);
    }




}