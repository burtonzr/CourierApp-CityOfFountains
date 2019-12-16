package com.example.cityoffountains.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cityoffountains.Database.DatabaseHelper;
import com.example.cityoffountains.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DatabaseHelper(this);

        final EditText username      = findViewById(R.id.loginUsername);
        final EditText loginPassword = findViewById(R.id.loginPassword);
        final Button loginButton     = findViewById(R.id.buttonLogin);
        final Button signupButton    = findViewById(R.id.signupButton);
        final Button couierButton    = findViewById(R.id.courier);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameString = username.getText().toString();
                String passwordString = loginPassword.getText().toString();
                if(usernameString.equals("") || passwordString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Username and password are required to login. ", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check = db.checkUsernamePassword(usernameString, passwordString);
                    if(check) {
                        Toast.makeText(getApplicationContext(), "You Successfully logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = HomeActivity.newIntent(view.getContext(), usernameString);
                        startActivityForResult(intent, 0);
                    } else {
                        Toast.makeText(getApplicationContext(), "You are not a user or your username / password combination is incorrect. Make sure to sign up and create an account.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignUpActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        couierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CourierActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
