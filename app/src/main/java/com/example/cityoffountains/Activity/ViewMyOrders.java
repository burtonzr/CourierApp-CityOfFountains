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

public class ViewMyOrders extends AppCompatActivity {
    private static final String EXTRA_USERNAME = "com.example.coffeeapp.username";
    public static String usernameText;

    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ViewOrdersAdapter adapter;
    DatabaseHelper db;
    List<Cart> carts = new ArrayList<>();
    TextView totalPriceText;

    public static Intent newIntentUsername(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, ViewMyOrders.class);
        usernameText = username;
        intent.putExtra(EXTRA_USERNAME, usernameText);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmyordersrecyclerview);

        db = new DatabaseHelper(this);

        mRecyclerView = findViewById(R.id.recycler_view_id);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        totalPriceText = findViewById(R.id.total);

        loadListCoffees();
    }

    public void loadListCoffees() {
        carts = new DatabaseHelper(this).getCartsByCustomerCompleted(usernameText);
        adapter = new ViewOrdersAdapter(carts, this);
        mRecyclerView.setAdapter(adapter);

        // Calculate total
        double total = 0;
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
}

class ViewOrdersAdapter extends RecyclerView.Adapter<MyOrdersViewHolder> {
    private List<Cart> listData;
    private Context context;

    public ViewOrdersAdapter(List<Cart> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public MyOrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.viewmyorders, parent, false);
        return new MyOrdersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyOrdersViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.CartItemCount.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        double price = (Double.parseDouble(listData.get(position).getProductPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.CartItemPrice.setText(nf.format(price));
        holder.CartItemName.setText(listData.get(position).getProductName());
        holder.CartItemOrderID.setText(String.valueOf(listData.get(position).getOrderID()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

class MyOrdersViewHolder extends RecyclerView.ViewHolder {
    public TextView CartItemName, CartItemPrice, CartItemOrderID;
    public ImageView CartItemCount;

    public MyOrdersViewHolder(View itemView) {
        super(itemView);
        CartItemName    = itemView.findViewById(R.id.cart_item_name);
        CartItemPrice   = itemView.findViewById(R.id.cart_item_price);
        CartItemCount   = itemView.findViewById(R.id.cart_item_count);
        CartItemOrderID = itemView.findViewById(R.id.order_id);
    }
}
