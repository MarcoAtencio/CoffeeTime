package com.example.coffeetime.ui.UserQR;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.coffeetime.R;
import com.example.coffeetime.model.User;
import com.squareup.picasso.Picasso;

public class UserQRActivity extends AppCompatActivity {

    ImageView qr, profileQr;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_qr_activity);
        qr = findViewById(R.id.qr_);
        profileQr = findViewById(R.id.profileQR_);
        addImage();
    }


    public void addImage() {

        user = new User();
        Picasso.get().load(user.getQrUser()).into(qr);
        Picasso.get().load(user.getPhotoUri()).into(profileQr);
    }

}