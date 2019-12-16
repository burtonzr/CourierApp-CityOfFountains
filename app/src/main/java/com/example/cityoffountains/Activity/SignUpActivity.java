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

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        db = new DatabaseHelper(this);

        final EditText name             = findViewById(R.id.name);
        final EditText email            = findViewById(R.id.email);
        final EditText phone            = findViewById(R.id.phone);
        final EditText username         = findViewById(R.id.username);
        final EditText password         = findViewById(R.id.password);
        final EditText confirmPassword  = findViewById(R.id.confirmpassword);
        final Button signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText             = name.getText().toString();
                String emailText            = email.getText().toString();
                String phoneText            = phone.getText().toString();
                String usernameText         = username.getText().toString();
                String passwordText         = password.getText().toString();
                String confirmPasswordText  = confirmPassword.getText().toString();

                if(nameText.equals("") || emailText.equals("") || phoneText.equals("") ||
                    usernameText.equals("") || passwordText.equals("") || confirmPasswordText.equals("")) {
                    Toast.makeText(getApplicationContext(), "No fields can be empty. ", Toast.LENGTH_SHORT).show();
                } else {
                    if(confirmPasswordText.equals(passwordText)) {
                        Boolean checkEmail = db.checkEmail(emailText);
                        Boolean checkUsername = db.checkUsername(usernameText);
                        if(checkEmail == true) {
                            if(checkUsername == true) {
                                Boolean insert = db.insert(nameText, emailText, phoneText, usernameText, passwordText);
                                if(insert == true) {
                                    Toast.makeText(getApplicationContext(), "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    setResult(RESULT_OK, intent);
                                    finish();
                                    // finish() closes any cursors and the method onDestroy() is executed.
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Username already exists.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email already exsits. ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
