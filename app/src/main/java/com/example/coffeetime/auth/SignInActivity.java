package com.example.coffeetime.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.WelcomeAdminActivity;
import com.example.coffeetime.admin.user.UserAdminActivity;
import com.example.coffeetime.model.User;
import com.example.coffeetime.ui.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.coffeetime.common.Functions.redirectActivity;

public class SignInActivity extends AppCompatActivity {

    EditText et_email, et_password;
    String email, password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        et_email = (EditText) findViewById(R.id.inputUser);
        et_password = (EditText) findViewById(R.id.idPwd);
    }


    public void login(View view) {
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        if (fieldsNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SignInActivity.this, email, Toast.LENGTH_SHORT).show();
                                if (email.equals("admin@CoffeeTime.com")) {
                                    redirectActivity(SignInActivity.this, WelcomeAdminActivity.class);
                                } else {
                                    redirectActivity(SignInActivity.this, WelcomeActivity.class, "email", email);
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignInActivity.this, "Ingreso los incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            Toast.makeText(SignInActivity.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
        }

    }


    public void registerUser(View view) {
        redirectActivity(this, SignUpActivity.class);
    }

    public boolean fieldsNotEmpty() {
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        return !email.isEmpty() && !password.isEmpty();
    }

}