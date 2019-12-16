package com.example.cityoffountains.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cityoffountains.R;

public class HomeActivity extends AppCompatActivity {

    private static final String EXTRA_USERNAME = "com.example.cityoffountains.username";
    public static String usernameText;

    public static Intent newIntent(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, HomeActivity.class);
        usernameText = username;
        intent.putExtra(EXTRA_USERNAME, usernameText);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        final Button createOrder  = findViewById(R.id.createOrder);
        final Button viewMyOrders = findViewById(R.id.viewMyOrders);

        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RestaurantListActivity.newIntentUsername(HomeActivity.this, usernameText);
                startActivityForResult(intent, 0);
            }
        });

        viewMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ViewMyOrders.newIntentUsername(HomeActivity.this, usernameText);
                startActivityForResult(intent, 0);
            }
        });
    }
}
