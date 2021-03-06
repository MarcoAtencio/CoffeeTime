package com.example.coffeetime.admin.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.home.HomeAdminActivity;
import com.example.coffeetime.admin.sales.SalesActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.auth.SignUpActivity;
import com.example.coffeetime.model.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.UUID;

import static com.example.coffeetime.common.Functions.redirectActivity;

public class ProductAdminActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText et_uid;
    EditText et_name;
    EditText et_price;
    EditText et_stock;
    EditText et_photoUri;
    String uid, name, photoUri;
    Spinner spinner1;
    String category;
    double price;
    int stock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_admin);
        et_uid = (EditText) findViewById(R.id.idproduct_);
        et_name = (EditText) findViewById(R.id.product_);
        et_stock = (EditText) findViewById(R.id.stock_);
        spinner1 = (Spinner) findViewById(R.id.spinner_);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.categories));
        spinner1.setAdapter(adapter);
        et_price = (EditText) findViewById(R.id.priceProducto_);
        et_photoUri = (EditText) findViewById(R.id.url_);

        initFirebase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_home:
                redirectActivity(this, HomeAdminActivity.class);
                break;

            case R.id.menu_product:
                return true;

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
        return super.onOptionsItemSelected(item);
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void saveProduct(View view) {
        if (fieldsNotEmpty()) {

            uid = et_uid.getText().toString();
            name = et_name.getText().toString();
            price = Double.parseDouble(et_price.getText().toString());
            category = (spinner1.getSelectedItemPosition() + 1) + "";
            stock = Integer.parseInt(et_stock.getText().toString());
            photoUri = et_photoUri.getText().toString();


            Product product = new Product();
            product.setUid(uid);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setPhotoURI(photoUri);
            product.setCategory(category);
            databaseReference.child("Product").child(product.getUid()).setValue(product);
            Toast.makeText(this, "Producto registrado", Toast.LENGTH_SHORT).show();
            fieldReset();
        } else {
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show();
        }


    }

    public void findProduct(View view) {

        String uid = et_uid.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        databaseReference.child("Product").child(product.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Product product = snapshot.getValue(Product.class);
                    et_uid.setText(product.getUid());
                    et_name.setText(product.getName());
                    et_price.setText("" + product.getPrice());
                    et_stock.setText("" + product.getStock());
                    spinner1.setSelection(Integer.parseInt(product.getCategory())-1);
                    et_photoUri.setText(product.getPhotoURI());

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    public void modifyProduct(View view) {
        uid = et_uid.getText().toString();
        name = et_name.getText().toString();
        price = Double.parseDouble(et_price.getText().toString());
        category = (spinner1.getSelectedItemPosition() + 1) + "";
        stock = Integer.parseInt(et_stock.getText().toString());

        photoUri = et_photoUri.getText().toString();
        Product product = new Product();
        product.setUid(uid);
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setStock(stock);

        product.setPhotoURI(photoUri);
        databaseReference.child("Product").child(product.getUid()).setValue(product);
        Toast.makeText(this, "Se modifico exitosamente", Toast.LENGTH_SHORT).show();
        fieldReset();

    }

    public void deleteProduct(View view) {

        String uid = et_uid.getText().toString();
        if (!uid.isEmpty()) {
            Product product = new Product();
            product.setUid(uid);
            databaseReference.child("Product").child(product.getUid()).removeValue();
            Toast.makeText(this, "Se elimino exitosamente", Toast.LENGTH_SHORT).show();
            fieldReset();
        } else {
            Toast.makeText(this, "Ingrese el id del producto", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean fieldsNotEmpty() {
        uid = et_uid.getText().toString();
        name = et_name.getText().toString();
        price = Double.parseDouble(et_price.getText().toString());
        stock = Integer.parseInt(et_stock.getText().toString());

        photoUri = et_photoUri.getText().toString();
        return !name.isEmpty() && !photoUri.isEmpty();
    }

    public void fieldReset() {
        et_uid.setText("");
        et_name.setText("");
        et_price.setText("");
        et_stock.setText("");
        et_photoUri.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}