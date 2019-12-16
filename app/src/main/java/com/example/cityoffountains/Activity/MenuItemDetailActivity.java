package com.example.cityoffountains.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.cityoffountains.Database.DatabaseHelper;
import com.example.cityoffountains.Model.Cart;
import com.example.cityoffountains.R;

public class MenuItemDetailActivity extends AppCompatActivity {

    DatabaseHelper db;
    public static Integer menuItem;
    public static Integer menu;
    public static Integer restaurant;
    public static String usernameText;
    private static final String EXTRA_MENU_ID = "com.example.coffeeapp.menu_id";
    private static final String EXTRA_MENU_ITEM_ID = "com.example.cityoffountains.menu_item_id";
    private static final String EXTRA_RESTAURANT_ID = "com.example.coffeeapp.restaurant_id";
    public static final String EXTRA_USERNAME = "com.example.cityoffountains.username";

    TextView name, price;
    ImageView image;
    ElegantNumberButton numberButton;
    Button addToCart;

    public static Intent newIntent(Context packageContext, Integer menuItemID, Integer menuID, Integer restaurantID, String username) {
        Intent intent = new Intent(packageContext, MenuItemDetailActivity.class);
        intent.putExtra(EXTRA_MENU_ID, menuID);
        intent.putExtra(EXTRA_MENU_ITEM_ID, menuItemID);
        intent.putExtra(EXTRA_RESTAURANT_ID, restaurantID);
        intent.putExtra(EXTRA_USERNAME, usernameText);
        menu = menuID;
        restaurant = restaurantID;
        usernameText = username;
        menuItem = menuItemID;
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item_detail);

        db = new DatabaseHelper(this);

        name         = findViewById(R.id.name);
        price        = findViewById(R.id.price);
        numberButton = findViewById(R.id.number_button);
        image        = findViewById(R.id.image);
        addToCart    = findViewById(R.id.add_to_cart);

        if(menuItem == 1 && menu == 3 && restaurant == 1) {
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatabaseHelper(getBaseContext()).addToCart(new Cart(
                            0,
                            "Breakfast Burger",
                            "4.50",
                            numberButton.getNumber(),
                            usernameText,
                            restaurant,
                            "0"
                    ));
                    Toast.makeText(getBaseContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            });
        } else if(menuItem == 2 && menu == 3 && restaurant == 1) {
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatabaseHelper(getBaseContext()).addToCart(new Cart(
                            0,
                            "Beet Burger",
                            "4.50",
                            numberButton.getNumber(),
                            usernameText,
                            restaurant,
                            "0"
                    ));
                    Toast.makeText(getBaseContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            });
        } else if(menuItem == 3 && menu == 3 && restaurant == 1) {
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatabaseHelper(getBaseContext()).addToCart(new Cart(
                            0,
                            "Black Bean Burger",
                            "4.50",
                            numberButton.getNumber(),
                            usernameText,
                            restaurant,
                            "0"
                    ));
                    Toast.makeText(getBaseContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            });
        } else if(menuItem == 4 && menu == 3 && restaurant == 1) {
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatabaseHelper(getBaseContext()).addToCart(new Cart(
                            0,
                            "Classic Burger",
                            "4.50",
                            numberButton.getNumber(),
                            usernameText,
                            restaurant,
                            "0"
                    ));
                    Toast.makeText(getBaseContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(savedInstanceState == null) {
            if(menuItem == null) {
                if (menuItem == 1 && menu == 3 && restaurant == 1) {
                    name.setText("Breakfast Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.breakfastburger_50);
                } else if(menuItem == 2 && menu == 3 && restaurant == 1) {
                    name.setText("Beet Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.beetburger_50);
                } else if(menuItem == 3 && menu == 3 && restaurant == 1) {
                    name.setText("Black Bean Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.blackbeanburger_50);
                } else if(menuItem == 4 && menu == 3 && restaurant == 1) {
                    name.setText("Classic Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.classicburger_50);
                }
            } else {
                if (menuItem == 1 && menu == 3 && restaurant == 1) {
                    name.setText("Breakfast Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.breakfastburger_50);
                } else if(menuItem == 2 && menu == 3 && restaurant == 1) {
                    name.setText("Beet Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.beetburger_50);
                } else if(menuItem == 3 && menu == 3 && restaurant == 1) {
                    name.setText("Black Bean Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.blackbeanburger_50);
                } else if(menuItem == 4 && menu == 3 && restaurant == 1) {
                    name.setText("Classic Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.classicburger_50);
                }
            }
        } else {
            if(menuItem == null) {
                if (menuItem == 1 && menu == 3 && restaurant == 1) {
                    name.setText("Breakfast Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.breakfastburger_50);
                } else if(menuItem == 2 && menu == 3 && restaurant == 1) {
                    name.setText("Beet Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.beetburger_50);
                } else if(menuItem == 3 && menu == 3 && restaurant == 1) {
                    name.setText("Black Bean Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.blackbeanburger_50);
                } else if(menuItem == 4 && menu == 3 && restaurant == 1) {
                    name.setText("Classic Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.classicburger_50);
                }
            } else {
                if (menuItem == 1 && menu == 3 && restaurant == 1) {
                    name.setText("Breakfast Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.breakfastburger_50);
                } else if(menuItem == 2 && menu == 3 && restaurant == 1) {
                    name.setText("Beet Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.beetburger_50);
                } else if(menuItem == 3 && menu == 3 && restaurant == 1) {
                    name.setText("Black Bean Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.blackbeanburger_50);
                } else if(menuItem == 4 && menu == 3 && restaurant == 1) {
                    name.setText("Classic Burger");
                    price.setText("4.50");
                    image.setImageResource(R.drawable.classicburger_50);
                }
            }
        }
    }
}
