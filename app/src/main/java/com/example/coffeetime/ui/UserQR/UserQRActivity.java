package com.example.coffeetime.ui.UserQR;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.coffeetime.state.InitialState.ownerUser;
import com.example.coffeetime.R;
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

}