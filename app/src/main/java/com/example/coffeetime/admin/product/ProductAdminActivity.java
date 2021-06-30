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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class ProductAdminActivity extends AppCompatActivity {

    EditText et_name, et_price, et_stock, et_category, et_photo_url;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_admin);
        et_name = (EditText) findViewById(R.id.txt_name);
        et_stock = (EditText) findViewById(R.id.txt_stock);
        et_category = (EditText) findViewById(R.id.txt_category);
        et_price = (EditText) findViewById(R.id.txt_precio);
        et_photo_url = (EditText) findViewById(R.id.txt_photo_url);
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
            String uid = UUID.randomUUID().toString();
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
        /*
        String uid = et_uid.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        databaseReference.child("Product").child(product.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Product product = snapshot.getValue(Product.class);
                    et_uid.setText(product.getUid());
                    et_name.setText(product.getName());
                    et_price.setText(product.getPrice());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
         */
    }

    public void modifyProduct(View view){
        /*
        String uid = et_uid.getText().toString();
        String name = et_name.getText().toString();
        String price = et_price.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        product.setName(name);
        product.setPrice(price);
        databaseReference.child("Product").child(product.getUid()).setValue(product);
        Toast.makeText(this,"Se registro exitosamente",Toast.LENGTH_SHORT).show();
        fieldReset();
         */
    }

    public void deleteProduct(View view){
        /*
        String uid = et_uid.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        databaseReference.child("Product").child(product.getUid()).removeValue();
        Toast.makeText(this,"Se elimino exitosamente",Toast.LENGTH_SHORT).show();
        fieldReset();*/
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
        et_name.setText("");
        et_price.setText("");
        et_category.setText("");
        et_stock.setText("");
        et_photo_url.setText("");
    }
}