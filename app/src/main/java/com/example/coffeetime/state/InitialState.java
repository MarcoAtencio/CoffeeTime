package com.example.coffeetime.state;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.coffeetime.logic.LProduct;
import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.Sale;
import com.example.coffeetime.model.User;
import com.example.coffeetime.ui.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.coffeetime.logic.LProduct.listProduct;
import static com.example.coffeetime.logic.LSale.listOwnPurchase;

public class InitialState {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseFirestore firebaseFirestore;
    public static User ownerUser;
    Context context;


    public InitialState(Context context_) {
        context = context_;
        initFirebase(context);
    }

    private void initFirebase(Context context) {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void stateListProducts() {
        databaseReference.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                listProduct.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    listProduct.add(product);
                }
            }
            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
            }
        });
    }

    public void stateListOwnPurchase(String email) {
        listOwnPurchase.clear();
        firebaseFirestore.collection("Sale").whereEqualTo("user", email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                Sale sale = new Sale();
                                Timestamp timestamp = (Timestamp) queryDocumentSnapshot.getData().get("dateSale");
                                sale.setDateSale(timestamp.toDate());
                                sale.setUid(queryDocumentSnapshot.getData().get("uid").toString());
                                sale.setState((boolean) queryDocumentSnapshot.getData().get("state"));
                                sale.setUser(queryDocumentSnapshot.getData().get("user").toString());
                                sale.setAmountTotal(Double.parseDouble(queryDocumentSnapshot.getData().get("amountTotal").toString()));

                                /*
                                sale.setListProduct((ArrayList<Product>) queryDocumentSnapshot.getData().get("listProduct"));

                                 */

                                listOwnPurchase.add(sale);

                                Toast.makeText(context, "" + timestamp.toDate(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                });

    }

    public void stateUser(String email) {
        ownerUser = new User();
        firebaseFirestore.collection("User").document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            ownerUser.setEmail(documentSnapshot.getString("email"));
                            ownerUser.setName(documentSnapshot.getString("name"));
                            ownerUser.setLastName(documentSnapshot.getString("lastName"));
                            ownerUser.setDateBirth(documentSnapshot.getString("dateBirth"));
                            ownerUser.setPhone(documentSnapshot.getString("phone"));
                            ownerUser.setPhotoUri(documentSnapshot.getString("photoUri"));
                            ownerUser.setQrUser(documentSnapshot.getString("qrUser"));
                            //Toast.makeText(context,"ownerUser",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
