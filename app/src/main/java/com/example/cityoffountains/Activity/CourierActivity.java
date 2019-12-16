package com.example.cityoffountains.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cityoffountains.Database.DatabaseHelper;
import com.example.cityoffountains.R;

public class CourierActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier_login);

        db = new DatabaseHelper(this);

        Button login      = findViewById(R.id.login_courier_button);
        Button newCourier = findViewById(R.id.new_courier_button);
        EditText username = findViewById(R.id.courier_username);
        EditText password = findViewById(R.id.courier_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        newCourier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CourierSignUpActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}
