package com.example.bonappetit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;

import com.example.bonappetit.MainActivity;
import com.example.bonappetit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcomeactivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcomeactivity);
        /*Spinner spinnerTable = findViewById(R.id.spinner_table);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.TableNo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerTable.setAdapter(adapter);
        spinnerTable.getOnItemSelectedListener();//select table will be send with there
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        public  void enter(View view){
        startActivity(new Intent(Welcomeactivity.this, MainActivity.class));
    }*/

    }
    @Override
     public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null) {
            startActivity(new Intent(Welcomeactivity.this, MainActivity.class));
        }
    }
    public void register(View view){
        startActivity(new Intent(Welcomeactivity.this, Registrationactivity.class));
    }
    //sign in
    public void login(View view){
        startActivity(new Intent(Welcomeactivity.this, Loginactivity.class));
    }
}