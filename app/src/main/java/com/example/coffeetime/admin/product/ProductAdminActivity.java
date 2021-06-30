package com.example.coffeetime.admin.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.home.HomeAdminActivity;
import com.example.coffeetime.admin.sales.SalesActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.UUID;

public class ProductAdminActivity extends AppCompatActivity {

    EditText et_codigo, et_name, et_price, et_stock, et_category, et_photo_url;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_admin);
        et_codigo = (EditText) findViewById(R.id.idproduct_);
        et_name = (EditText) findViewById(R.id.product_);
        et_stock = (EditText) findViewById(R.id.stock_);
        et_category = (EditText) findViewById(R.id.category_);
        et_price = (EditText) findViewById(R.id.priceProducto_);
        et_photo_url = (EditText) findViewById(R.id.url_);
        initFirebase();
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
                return true;

            case R.id.menu_user:
                intent = new Intent(this, UserAdminActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_sales:
                intent = new Intent(this, SalesActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void saveProduct(View view){
        if (fieldValidate() == 1){
            String uid = et_codigo.getText().toString();
            String name = et_name.getText().toString();
            String price =  et_price.getText().toString();
            String stock = et_stock.getText().toString();
            String category = et_category.getText().toString();
            String photoUrl = et_photo_url.getText().toString();

            Product product = new Product();
            product.setUid(uid);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setPhotoURI(photoUrl);
            product.setCategory(category);
            databaseReference.child("Product").child(product.getUid()).setValue(product);
            Toast.makeText(this,"Se registro exitosamente",Toast.LENGTH_SHORT).show();
            fieldReset();
        }
    }

    public void findProduct(View view){

        String uid = et_codigo.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        databaseReference.child("Product").child(product.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Product product = snapshot.getValue(Product.class);
                    et_codigo.setText(product.getUid());
                    et_name.setText(product.getName());
                    et_price.setText(product.getPrice());
                    et_stock.setText(product.getStock());
                    et_category.setText(product.getCategory());
                    et_photo_url.setText(product.getPhotoURI());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    public void modifyProduct(View view){

        String uid = et_codigo.getText().toString();
        String name = et_name.getText().toString();
        String price = et_price.getText().toString();
        String stock = et_stock.getText().toString();
        String category = et_category.getText().toString();
        String photoUrl = et_photo_url.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        product.setPhotoURI(photoUrl);
        databaseReference.child("Product").child(product.getUid()).setValue(product);
        Toast.makeText(this,"Se registro exitosamente",Toast.LENGTH_SHORT).show();
        fieldReset();

    }

    public void deleteProduct(View view){

        String uid = et_codigo.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        databaseReference.child("Product").child(product.getUid()).removeValue();
        Toast.makeText(this,"Se elimino exitosamente",Toast.LENGTH_SHORT).show();
        fieldReset();
    }

    public int fieldValidate(){
        String name = et_name.getText().toString();
        String price = et_price.getText().toString();

        if ( name.isEmpty() && price.isEmpty()){
            Toast.makeText(this,"Llene los campos",Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }

    public void fieldReset(){
        et_codigo.setText("");
        et_name.setText("");
        et_price.setText("");
        et_category.setText("");
        et_stock.setText("");
        et_photo_url.setText("");
    }
}