package com.example.cityoffountains.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cityoffountains.Database.DatabaseHelper;
import com.example.cityoffountains.Model.Order;
import com.example.cityoffountains.R;

public class PaymentActivity extends AppCompatActivity {

    DatabaseHelper db;

    Button btnPlaceOrder;
    EditText courier, DeliveryAddress, cardNumber;

    public static Double totalAmount;

    public static String usernameText;
    private static final String EXTRA_USERNAME = "com.example.coffeeapp.Fragments.username";

    public static Intent newIntentUsername(Context packageContext, String username, Double total) {
        Intent intent = new Intent(packageContext, PaymentActivity.class);
        usernameText = username;
        totalAmount = total;
        intent.putExtra(EXTRA_USERNAME, usernameText);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        db = new DatabaseHelper(this);

        courier         = findViewById(R.id.courier);
        btnPlaceOrder   = findViewById(R.id.btnPlaceOrder);
        DeliveryAddress = findViewById(R.id.deliveryAddress);
        cardNumber      = findViewById(R.id.card_number);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String courierText         = courier.getText().toString();
                final String DeliveryAddressText = DeliveryAddress.getText().toString();
                final String cardNumberText      = cardNumber.getText().toString();
                if(courierText.equals("")) {
                    Toast.makeText(getBaseContext(), "You must choose a courier. ", Toast.LENGTH_SHORT).show();
                } else {
                    if(DeliveryAddressText.equals("")) {
                        Toast.makeText(getBaseContext(), "Delivery Address is required. ", Toast.LENGTH_LONG).show();
                    } else {
                        if(cardNumberText.equals("")) {
                            Toast.makeText(getBaseContext(), "Credit Card Number is required. ", Toast.LENGTH_LONG).show();
                        }  else {
                            new DatabaseHelper(getBaseContext()).createOrder(new Order(
                                    usernameText,
                                    DeliveryAddressText,
                                    courierText,
                                    String.valueOf(totalAmount),
                                    "Credit Card",
                                    cardNumberText,
                                    "1"
                            ));
                            db.setOrderID();
                            db.setStatus(usernameText);
                            Toast.makeText(getBaseContext(), "Order Completed!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PaymentActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}
