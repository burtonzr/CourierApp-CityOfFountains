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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityoffountains.Data.menuData;
import com.example.cityoffountains.Model.menuModel;
import com.example.cityoffountains.R;

import java.util.ArrayList;
import java.util.List;

public class MenuListActivity extends AppCompatActivity {

    public static Integer RestaurantIDIntent;
    public static String usernameText;
    public static Integer MenuID;
    private static ArrayList<menuModel> data;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private static final String EXTRA_RESTAURANT_ID = "com.example.cityoffountains.restaurant_id";
    private static final String EXTRA_MENU_ID = "com.example.cityoffountains.menu_id";
    public static final String EXTRA_USERNAME = "com.example.cityoffountains.username";

    public static Intent newIntent(Context packageContext, Integer restaurantID, String username) {
        Intent intent = new Intent(packageContext, MenuListActivity.class);
        RestaurantIDIntent = restaurantID;
        usernameText = username;
        intent.putExtra(EXTRA_RESTAURANT_ID, restaurantID);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }

    // Context: It is an interface to global information about an application environment.
    // It allows access to application-specific resources and classes, as well as up-calls
    // for application-level operations such as launching activities and receiving intents.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_recyclerview);

        mRecyclerView = findViewById(R.id.menu_recycler_view_id);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        data = new ArrayList<>();

        for(int i = 0; i < menuData.names.length; i++) {
            data.add(new menuModel(
                    menuData.ids[i],
                    menuData.names[i],
                    menuData.images[i]
            ));
        }

        if(mAdapter == null) {
            mAdapter = new Adapter(data);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            // If the adapter exists, then use the notifyDataSetChanged() method to notify
            // the attached observers that the underlying data has changed and that any View
            // reflecting the data set should refresh itself.
        }
    }

    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private menuModel mMenuModel;
        private ImageView menuImageView;
        private TextView menuTextView;

        public void bind(menuModel menu) {
            mMenuModel = menu;
            menuImageView.setImageResource(mMenuModel.getImage());
            menuTextView.setText(mMenuModel.getName());
        }

        // It is a view that can contain other views (called children).
        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.menu_list, parent, false));
            itemView.setOnClickListener(this);

            menuImageView = itemView.findViewById(R.id.menu_image_view);
            menuTextView = itemView.findViewById(R.id.menu_text_view);
        }

        @Override
        public void onClick(View view) {
            Intent intent = MenuItemActivity.newIntent(MenuListActivity.this, mMenuModel.getId(), RestaurantIDIntent, usernameText);
            Bundle args = new Bundle();
            MenuID = mMenuModel.getId();
            args.putSerializable(EXTRA_RESTAURANT_ID, RestaurantIDIntent);
            args.putSerializable(EXTRA_USERNAME, usernameText);
            args.putSerializable(EXTRA_MENU_ID, MenuID);
            startActivity(intent, args);
        }
    }

    private class Adapter extends RecyclerView.Adapter<Holder> {
        private List<menuModel> data;

        public Adapter(List<menuModel> menu) {
            data = menu;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MenuListActivity.this);

            return new Holder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            menuModel model = data.get(position);
            holder.bind(model);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
