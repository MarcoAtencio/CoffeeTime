package com.example.coffeetime.ui.UserQR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.coffeetime.state.InitialState.ownerUser;
import com.example.coffeetime.R;
import com.example.coffeetime.admin.product.ProductAdminActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.ui.cart.CartActivity;
import com.example.coffeetime.ui.history.HistoryActivity;
import com.example.coffeetime.ui.home.HomeActivity;
import com.example.coffeetime.ui.profile.ProfileActivity;
import com.squareup.picasso.Picasso;

public class UserQRActivity extends AppCompatActivity {

    ImageView qr, profileQr;
    TextView nameQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_qr_activity);
        qr = findViewById(R.id.qr_);
        profileQr = findViewById(R.id.profileQR_);
        nameQr = findViewById(R.id.txtNameQr_);

        addImage();
    }

    public void addImage() {
        nameQr.setText(ownerUser.getName() + " " + ownerUser.getLastName());
        Picasso.get().load(ownerUser.getQrUser()).into(qr);
        Picasso.get().load(ownerUser.getPhotoUri()).into(profileQr);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
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

            case R.id.menu_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_QR:
                return true;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }

}