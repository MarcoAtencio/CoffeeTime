package com.example.coffeetime.ui.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.logic.LCart;
import com.example.coffeetime.logic.LSale;
import com.example.coffeetime.model.Sale;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.coffeetime.logic.LCart.cart;

public class payActivity extends AppCompatActivity {

    TextView totalPay, cantPay;
    Sale sale;
    LSale lSale;
    LCart lcart = new LCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        totalPay = findViewById(R.id.pricePay);
        cantPay = findViewById(R.id.titlePay);

        cantPay.setText(cart.size() + " " + "Articulos en tu carrito");
        totalPay.setText("S/. " + lcart.Total());
        lSale = new LSale();

    }

    public void saveSale(View view){
        lSale.registerSale();
        Toast.makeText(this, "Gracias por su compra", Toast.LENGTH_SHORT).show();
    }




}