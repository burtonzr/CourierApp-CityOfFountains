package com.example.cityoffountains.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.cityoffountains.Database.DatabaseHelper;
import com.example.cityoffountains.Model.Cart;
import com.example.cityoffountains.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewListCart;
    RecyclerView.LayoutManager mLayoutManager;
    DatabaseHelper db;
    List<Cart> carts = new ArrayList<>();

    CartAdapter adapter;

    TextView totalPriceText;
    Button btnPlaceOrder;

    double total;

    public static String usernameText;
    private static final String EXTRA_USERNAME = "com.example.coffeeapp.username";
    private static final String EXTRA_TOTAL = "com.example.coffeeapp.total";

    public static Intent newIntentUsername(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, CartActivity.class);
        usernameText = username;
        intent.putExtra(EXTRA_USERNAME, usernameText);
        return intent;
    }

    private void loadListCoffees() {
        carts = new DatabaseHelper(this).getCartsByCustomerStatus(usernameText);
        adapter = new CartAdapter(carts, this);
        mRecyclerViewListCart.setAdapter(adapter);

        // calculate total
        total = 0;
        for(Cart cart: carts) {
            try {
                total += (Double.parseDouble(cart.getProductPrice())) * (Integer.parseInt(cart.getQuantity()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        totalPriceText.setText(numberFormat.format(total));
        totalPriceText.setText(String.valueOf(total));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = new DatabaseHelper(this);

        mRecyclerViewListCart = findViewById(R.id.recycler_view_id);
        mRecyclerViewListCart.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListCart.setLayoutManager(mLayoutManager);

        totalPriceText = findViewById(R.id.total);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        loadListCoffees();

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = PaymentActivity.newIntentUsername(CartActivity.this, usernameText, total);
                Bundle args = new Bundle();
                args.putSerializable(EXTRA_TOTAL, total);
                args.putSerializable(EXTRA_USERNAME, usernameText);
                startActivity(intent, args);
            }
        });
    }
}

class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Cart> listData;
    private Context context;

    public CartAdapter(List<Cart> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.CartItemCount.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        double price = (Double.parseDouble(listData.get(position).getProductPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.CartItemPrice.setText(fmt.format(price));
        holder.CartItemName.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder {

    public TextView CartItemName, CartItemPrice;
    public ImageView CartItemCount;

    public CartViewHolder(View itemView) {
        super(itemView);
        CartItemName    = itemView.findViewById(R.id.cart_item_name);
        CartItemPrice   = itemView.findViewById(R.id.cart_item_price);
        CartItemCount   = itemView.findViewById(R.id.cart_item_quantity);
    }
}