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
import com.google.android.gms.tasks.OnSuccessListener;
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

import static com.example.coffeetime.common.Functions.redirectActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText et_email, et_password, et_name, et_lastName, et_phone, et_dateBirth;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    User user;
    String email, password, name, lastName, phone, dateBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        et_email = (EditText) findViewById(R.id.txtUser);
        et_password = (EditText) findViewById(R.id.txtpassword);
        et_name = (EditText) findViewById(R.id.id_Name);
        et_lastName = (EditText) findViewById(R.id.id_lastName);
        et_phone = (EditText) findViewById(R.id.id_phone);
        et_dateBirth = (EditText) findViewById(R.id.id_dateBirth);
        firebaseFirestore = FirebaseFirestore.getInstance();

    }


    public void registerUser(View view) {
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        name = et_name.getText().toString().trim();
        lastName = et_lastName.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        dateBirth = et_dateBirth.getText().toString().trim();

        if (fieldsNotEmpty()) {
            user = new User();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SignUpActivity.this, "Se a registrado exitosamente", Toast.LENGTH_SHORT).show();
                                redirectActivity(SignUpActivity.this, SignInActivity.class);

                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Ya esta registrado este usuario", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Ocurrio algo en el proceso", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

            user.setName(name);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setDateBirth(dateBirth);
            user.setEmail(email);
            firebaseFirestore.collection("User").document(user.getEmail()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean fieldsNotEmpty() {
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        name = et_name.getText().toString().trim();
        lastName = et_lastName.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        dateBirth = et_dateBirth.getText().toString().trim();
        return !email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && !phone.isEmpty() && !dateBirth.isEmpty();
    }

}