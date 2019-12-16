package com.example.cityoffountains.Activity;

import android.content.Context;
import android.content.Intent;
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

import com.example.cityoffountains.Data.restaurantData;
import com.example.cityoffountains.Model.restaurantModel;
import com.example.cityoffountains.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    public static String usernameText;
    public static Integer setRestaurantID;
    private static ArrayList<restaurantModel> data;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private static final String EXTRA_USERNAME = "com.example.cityoffountains.username";
    private static final String EXTRA_RESTAURANT_ID = "com.example.cityoffountains.coffee_id";

    public static Intent newIntentUsername(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, RestaurantListActivity.class);
        usernameText = username;
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewlayout);

        mRecyclerView = findViewById(R.id.recycler_view_id);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Button goToCart = findViewById(R.id.goToCart);
        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = CartActivity.newIntentUsername(view.getContext(), usernameText);
                startActivityForResult(cartIntent, 0);
            }
        });

        data = new ArrayList<>();

        for(int i = 0; i < restaurantData.restaurantNames.length; i++) {
            data.add(new restaurantModel(
                    restaurantData.restaurantNames[i],
                    restaurantData.ids[i],
                    restaurantData.images[i]
            ));
        }

        if(mAdapter == null) {
            mAdapter = new Adapter(data);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private restaurantModel mRestaurant;
        private ImageView mImageView;
        private TextView mTextView;

        public void bind(restaurantModel restaurant) {
            mRestaurant = restaurant;
            mImageView.setImageResource(mRestaurant.getImage());
            mTextView.setText(mRestaurant.getRestaurantName());
        }

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.restaurant_list, parent, false));
            itemView.setOnClickListener(this);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextView = itemView.findViewById(R.id.text_view);
        }

        @Override
        public void onClick(View view) {
            Intent intent = MenuListActivity.newIntent(RestaurantListActivity.this, mRestaurant.getId(), usernameText);
            Bundle args = new Bundle();
            setRestaurantID = mRestaurant.getId();
            args.putSerializable(EXTRA_USERNAME, setRestaurantID);
            args.putSerializable(EXTRA_RESTAURANT_ID, usernameText);
            startActivityForResult(intent, 0);
        }
    }

    private class Adapter extends RecyclerView.Adapter<Holder> {
        private List<restaurantModel> data;

        public Adapter(List<restaurantModel> restaurant) {
            data = restaurant;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(RestaurantListActivity.this);

            return new Holder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            restaurantModel model = data.get(position);
            holder.bind(model);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
