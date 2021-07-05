package com.example.coffeetime.ui.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.User;
import com.example.coffeetime.ui.UserQR.UserQRActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import static com.example.coffeetime.common.Functions.closeDrawer;
import static com.example.coffeetime.common.Functions.logout;
import static com.example.coffeetime.common.Functions.openDrawer;
import static com.example.coffeetime.common.Functions.redirectActivity;
import static com.example.coffeetime.state.InitialState.ownerUser;

public class ProfileActivity extends AppCompatActivity {
    EditText et_name, et_lastName, et_phone, et_date;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseFirestore firebaseFirestore;
    String name, lastName, phone, dateBirth;
    DrawerLayout drawerLayout;
    ImageView d_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseFirestore = FirebaseFirestore.getInstance();
        et_name = (EditText) findViewById(R.id.et_nameOwnUser);
        et_lastName = (EditText) findViewById(R.id.et_lastNameOwnUser);
        et_phone = (EditText) findViewById(R.id.et_phoneOwnUser);
        et_date = (EditText) findViewById(R.id.et_dateBirthOwnUser);
        drawerLayout = findViewById(R.id.drawer_layout);
        et_name.setText(ownerUser.getName());
        et_lastName.setText(ownerUser.getLastName());
        et_date.setText(ownerUser.getDateBirth());
        et_phone.setText(ownerUser.getPhone());
        d_photo = findViewById(R.id.drawerPhoto);
        Picasso.get().load(ownerUser.getPhotoUri()).into(d_photo);
        initFirebase();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_home:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_QR:
                intent = new Intent(this, UserQRActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_profile:
                return true;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void updateProfile(View view) {
        name = et_name.getText().toString();
        lastName = et_lastName.getText().toString();
        phone = et_phone.getText().toString();
        dateBirth = et_date.getText().toString();
        ownerUser.setName(name);
        ownerUser.setLastName(lastName);
        ownerUser.setPhone(phone);
        ownerUser.setDateBirth(dateBirth);

        firebaseFirestore.collection("User").document(ownerUser.getEmail()).set(ownerUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProfileActivity.this, "updateProfile", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }


    public void ClickLogo(View view) {
        redirectActivity(this, UserQRActivity.class);
    }


    public void ClickHome(View view) {
        redirectActivity(this, HomeActivity.class);
    }

    public void ClickCart(View view) {
        redirectActivity(this, CartActivity.class);
    }


    public void ClickHistory(View view) {
        redirectActivity(this, HistoryActivity.class);
    }

    public void ClickProfile(View view) {
        closeDrawer(drawerLayout);
    }

    public void ClickExit(View view) {
        logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void ClickLogout(View view) {
        redirectActivity(this, SignInActivity.class);
    }

}


