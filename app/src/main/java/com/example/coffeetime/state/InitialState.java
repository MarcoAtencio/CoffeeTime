package com.example.coffeetime.state;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.coffeetime.logic.LProduct;
import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.User;
import com.example.coffeetime.ui.WelcomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.coffeetime.logic.LProduct.listProduct;

public class InitialState {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseFirestore firebaseFirestore;
    public static User ownerUser;
    Context context;


    public InitialState(Context context_){
        context = context_;
        initFirebase(context);
    }

    private void initFirebase(Context context){
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void stateListProducts(){
        databaseReference.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                listProduct.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    listProduct.add(product);
                }
            }
            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
            }
        });
    }

    public void stateUser(String email){

        ownerUser = new User();
        firebaseFirestore.collection("User").document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            ownerUser.setEmail(documentSnapshot.getString("email"));
                            ownerUser.setName(documentSnapshot.getString("name"));
                            ownerUser.setLastName(documentSnapshot.getString("lastName"));
                            ownerUser.setDateBirth(documentSnapshot.getString("dateBirth"));
                            ownerUser.setPhone(documentSnapshot.getString("phone"));

                            Toast.makeText(context,"ownerUser",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
