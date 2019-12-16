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

public class CourierSignUpActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier_signup);

        db = new DatabaseHelper(this);

        final EditText name     = findViewById(R.id.name);
        final EditText phone    = findViewById(R.id.phoneNumber);
        final EditText email    = findViewById(R.id.emailAddress);
        final EditText vehicle  = findViewById(R.id.vehicle);
        final EditText age      = findViewById(R.id.age);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText confirm  = findViewById(R.id.confirmPassword);
        Button signup     = findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText      = name.getText().toString();
                String phoneText     = phone.getText().toString();
                String emailText     = email.getText().toString();
                String vehicleText   = vehicle.getText().toString();
                String ageText       = age.getText().toString();
                String usernameText  = username.getText().toString();
                String passwordText  = password.getText().toString();
                String confirmText   = confirm.getText().toString();
                if(nameText.equals("") || phoneText.equals("") || emailText.equals("") || vehicleText.equals("") ||
                        ageText.equals("") || usernameText.equals("") || passwordText.equals("") || confirmText.equals("")) {
                    Toast.makeText(getApplicationContext(), "No fields can be empty. ", Toast.LENGTH_SHORT).show();
                } else {
                    if(confirmText.equals(passwordText)) {
                        Boolean checkEmail    = db.checkEmail(emailText);
                        Boolean checkUsername = db.checkUsername(usernameText);
                        if(checkEmail == true) {
                            if(checkUsername == true) {
                                Boolean insert = db.insertCourier(nameText, phoneText, emailText, vehicleText, ageText, usernameText, passwordText);
                                if(insert == true) {
                                    Toast.makeText(getApplicationContext(), "Sign Up Successful. ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Username already exists.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email address already exists. ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
