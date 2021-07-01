package com.example.coffeetime.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {
    EditText  et_email, et_password, et_name, et_lastName, et_phone, et_dateBirth;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        et_email  = (EditText) findViewById(R.id.txtUser);
        et_password = (EditText) findViewById(R.id.txtpassword);
        et_name = (EditText) findViewById(R.id.id_Name);
        et_lastName = (EditText) findViewById(R.id.id_lastName);
        et_phone = (EditText) findViewById(R.id.id_phone);
        et_dateBirth = (EditText) findViewById(R.id.id_dateBirth);
        firebaseFirestore = FirebaseFirestore.getInstance();

    }


    public void registerUser(View view){
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String name = et_name.getText().toString().trim();
        String lastName = et_lastName.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String dateBirth = et_dateBirth.getText().toString().trim();


        user = new User();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this,"Se a registrado exitosamente",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), SignInActivity.class);
                            startActivity(intent);
                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this,"Ya esta registrado este usuario",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this,"Ocurrio algo en el proceso",Toast.LENGTH_SHORT).show();
                            }

                        }

                        // ...
                    }

                });


        user.setName(name);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setDateBirth(dateBirth);
        user.setEmail(email);

        firebaseFirestore.collection("User").document(user.getEmail()).set(user);

    }


}