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
import com.example.coffeetime.ui.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignInActivity extends AppCompatActivity {

    EditText et_email, et_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        et_email  = (EditText) findViewById(R.id.inputUser);
        et_password = (EditText) findViewById(R.id.idPwd);
    }


    public void login(View view){
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignInActivity.this,email,Toast.LENGTH_SHORT).show();
                            Intent intent;
                            if(email.equals("marco1@hotmail.com")){
                                intent = new Intent(getApplication(), WelcomeAdminActivity.class);
                            }
                            else {
                                intent = new Intent(getApplication(), WelcomeActivity.class);
                            }
                            startActivity(intent);

                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignInActivity.this,"Ya esta registrado este usuario",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignInActivity.this,"Ocurrio algo en el proceso",Toast.LENGTH_SHORT).show();
                            }

                        }

                        // ...
                    }
                });

    }



    public void  registerUser(View view){

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}