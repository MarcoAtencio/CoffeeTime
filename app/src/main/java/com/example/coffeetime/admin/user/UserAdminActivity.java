package com.example.coffeetime.admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.home.HomeAdminActivity;
import com.example.coffeetime.admin.product.ProductAdminActivity;
import com.example.coffeetime.admin.sales.SalesActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;

import static com.example.coffeetime.common.Functions.redirectActivity;

public class UserAdminActivity extends AppCompatActivity {

    EditText et_name, et_lastName, et_email, et_phone, et_date;
    FirebaseFirestore firebaseFirestore;
    String name, lastName, email, phone, dateBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);
        et_name = (EditText) findViewById(R.id.txt_name);
        et_lastName = (EditText) findViewById(R.id.txt_lastName);
        et_email = (EditText) findViewById(R.id.txt_email);
        et_phone = (EditText) findViewById(R.id.txt_phone);
        et_date = (EditText) findViewById(R.id.txt_date);
        firebaseFirestore = FirebaseFirestore.getInstance();

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
                redirectActivity(this, ProductAdminActivity.class);
                break;

            case R.id.menu_user:
                return true;

            case R.id.menu_sales:
                redirectActivity(this, SalesActivity.class);
                break;

            case R.id.menu_logout:
                redirectActivity(this, SignInActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void findUser(View view) {

        email = et_email.getText().toString();
        User user = new User();
        Toast.makeText(UserAdminActivity.this, email, Toast.LENGTH_SHORT).show();
        firebaseFirestore.collection("User").document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            et_name.setText(documentSnapshot.getString("name"));
                            et_lastName.setText(documentSnapshot.getString("lastName"));
                            et_phone.setText(documentSnapshot.getString("phone"));
                            et_date.setText(documentSnapshot.getString("dateBirth"));

                            Toast.makeText(UserAdminActivity.this, "exito", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    public void modifyUser(View view) {


        name = et_name.getText().toString();
        lastName = et_lastName.getText().toString();
        email = et_email.getText().toString();
        phone = et_phone.getText().toString();
        dateBirth = et_date.getText().toString();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDateBirth(dateBirth);
        firebaseFirestore.collection("User").document(email).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserAdminActivity.this, "exito", Toast.LENGTH_SHORT).show();
                    }
                });
        fieldReset();


    }

    /*
    public void deleteUser(View view){


        String uid = et_code.getText().toString();
        User user= new User();
        user.setUid(uid);
        databaseReference.child("User").child(user.getUid()).removeValue();
        Toast.makeText(this,"Se elimino exitosamente",Toast.LENGTH_SHORT).show();
        fieldReset();

    }

  */
    public void fieldReset() {
        et_name.setText("");
        et_lastName.setText("");
        et_email.setText("");
        et_phone.setText("");
        et_date.setText("");
    }
}