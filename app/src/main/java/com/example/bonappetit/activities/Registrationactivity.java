package com.example.bonappetit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.bonappetit.MainActivity;
import com.example.bonappetit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registrationactivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailid, password,address,phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationactivity);

        mAuth = FirebaseAuth.getInstance();
        emailid = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.editTextPhone);

    }

    public void lsb(View view) {
        startActivity(new Intent(Registrationactivity.this, Loginactivity.class));
        finish();
    }

    public void onClick(View v) {
        String mail = emailid.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String addre = address.getText().toString().trim();
        String ph_no = phone.getText().toString().trim();
        if (mail.isEmpty() || pass.isEmpty() || addre.isEmpty() || ph_no.isEmpty()) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        }
        else if (pass.length()<6){
            Toast.makeText(getApplicationContext(), "Password should be greater than 5", Toast.LENGTH_SHORT).show();
        }
        else if(ph_no.length()<10){
            Toast.makeText(getApplicationContext(), "Phone number must be of 9 number", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("working", "onComplete: ");
            mAuth.createUserWithEmailAndPassword(emailid.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("working", "onComplete: ");
                                Toast.makeText(Registrationactivity.this, "Registration successful! Login Again.",
                                        Toast.LENGTH_SHORT).show();
                                String email = mAuth.getCurrentUser().getEmail();
                                String uid = mAuth.getCurrentUser().getUid();
                                Map<String, Object> user = new HashMap<>();
                                user.put("Uid", uid);
                                user.put("Phone_number", ph_no);
                                user.put("Address", addre);
                                FirebaseFirestore.getInstance().collection("user").document(email).set(user);
                                startActivity(new Intent(Registrationactivity.this, MainActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Registrationactivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationactivity);
    }

    public void login(View view){
        startActivity(new Intent(Registrationactivity.this, Loginactivity.class));
        finish();
    }
    public void lsb(View view){
        startActivity(new Intent(Registrationactivity.this, Loginactivity.class));
        finish();
    }*/

}