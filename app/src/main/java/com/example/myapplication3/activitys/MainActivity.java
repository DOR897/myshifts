package com.example.myapplication3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.models.Person;
import com.example.myapplication3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passText;

    private String idText;
    private boolean running ;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

    }

    public void funcLogin(EditText emailText, EditText passText , View view){
        String email =emailText.getText().toString().trim();
        String password =passText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Navigation.findNavController(view).navigate(R.id.action_logInFragment2_to_timeFragment);
                        } else {
                            Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void funcReg(EditText emailText,EditText passText ,View view){
        String email =emailText.getText().toString().trim();
        String password =passText.getText().toString().trim();
        Log.d("result",email+" "+password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment2_to_timeFragment);
                        } else {
                            Toast.makeText(MainActivity.this,"Register failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void readDB(TextView email, TextView phone, TextView address, EditText search){

        myRef.addValueEventListener(new ValueEventListener() {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Users").child(search.getText().toString());
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);
                email.setText(value.email);
                phone.setText(value.phone);
                address.setText(value.address);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }


    public void writeDB(EditText emailText, EditText phoneText, EditText addressText, EditText idText) {
        // Write a message to the database
        Person p =  new Person(emailText.getText().toString(),
                                phoneText.getText().toString(),
                                 addressText.getText().toString(),
                                  idText.getText().toString()
        );
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(idText.getText().toString());

        myRef.setValue(p);
    }



}

