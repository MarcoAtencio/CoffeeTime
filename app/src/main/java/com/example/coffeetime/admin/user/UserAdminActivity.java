package com.example.coffeetime.admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeetime.R;
import com.example.coffeetime.admin.home.HomeAdminActivity;
import com.example.coffeetime.admin.product.ProductAdminActivity;
import com.example.coffeetime.admin.sales.SalesActivity;
import com.example.coffeetime.auth.SignInActivity;
import com.example.coffeetime.model.Product;
import com.example.coffeetime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;

public class UserAdminActivity extends AppCompatActivity {

    EditText  et_code, et_name, et_lastName, et_email, et_phone, et_date;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);
        et_code = (EditText) findViewById(R.id.txt_code);
        et_name = (EditText) findViewById(R.id.txt_name);
        et_lastName = (EditText) findViewById(R.id.txt_lastName);
        et_email = (EditText) findViewById(R.id.txt_email);
        et_phone = (EditText) findViewById(R.id.txt_phone);
        et_date = (EditText) findViewById(R.id.txt_date);
        firebaseFirestore = FirebaseFirestore.getInstance();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){

            case R.id.menu_home:
                intent = new Intent(this, HomeAdminActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_product:
                intent = new Intent(this, ProductAdminActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_user:
                return true;

            case R.id.menu_sales:
                intent = new Intent(this, SalesActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_logout:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
        }
        return  super.onOptionsItemSelected(item);
    }





        public void findUser(View view){

            String email = et_email.getText().toString();
            User user= new User();
            Toast.makeText(UserAdminActivity.this,email,Toast.LENGTH_SHORT).show();
            firebaseFirestore.collection("User").document(email).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                et_name.setText(documentSnapshot.getString("name"));
                                
                                Toast.makeText(UserAdminActivity.this,"exito",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            /*
              databaseReference.child("User").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        User user = snapshot.getValue(User.class);
                        et_name.setText(user.getName());
                        et_lastName.setText(user.getLastName());
                        et_email.setText(user.getEmail());
                        et_phone.setText(user.getPhone());
                        et_date.setText(user.getDateBirth());
                    }
                }
                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
             */



        }

    /*
  public void modifyUser(View view){


      String uid = et_code.getText().toString();
      String name = et_name.getText().toString();
      String lastName = et_lastName.getText().toString();
      String email = et_email.getText().toString();
      String phone = et_phone.getText().toString();
      String dateBirth = et_date.getText().toString();

      User user= new User();

      user.setName(name);
      user.setLastName(lastName);
      user.setEmail(email);
      user.setPhone(phone);
      user.setDateBirth(dateBirth);
      databaseReference.child("User").child(user.getUid()).setValue(user);
      Toast.makeText(this,"Se registro exitosamente",Toast.LENGTH_SHORT).show();
      fieldReset();



  }

  public void deleteUser(View view){


      String uid = et_code.getText().toString();
      User user= new User();
      user.setUid(uid);
      databaseReference.child("User").child(user.getUid()).removeValue();
      Toast.makeText(this,"Se elimino exitosamente",Toast.LENGTH_SHORT).show();
      fieldReset();

  }

*/
    public void fieldReset(){

        et_name.setText("");
        et_lastName.setText("");
        et_email.setText("");
        et_phone.setText("");
        et_date.setText("");
    }
}