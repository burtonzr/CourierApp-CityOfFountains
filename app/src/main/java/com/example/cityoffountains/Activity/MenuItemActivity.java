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

import com.example.cityoffountains.Data.menuItemDrink1;
import com.example.cityoffountains.Data.menuItemDrink2;
import com.example.cityoffountains.Data.menuItemDrink3;
import com.example.cityoffountains.Data.menuItemDrink4;
import com.example.cityoffountains.Data.menuItemLunch1;
import com.example.cityoffountains.Data.menuItemLunch2;
import com.example.cityoffountains.Data.menuItemLunch3;
import com.example.cityoffountains.Data.menuItemLunch4;
import com.example.cityoffountains.Data.menuItemDinner1;
import com.example.cityoffountains.Data.menuItemDinner2;
import com.example.cityoffountains.Data.menuItemDinner3;
import com.example.cityoffountains.Data.menuItemDinner4;
import com.example.cityoffountains.Data.menuItemDessert1;
import com.example.cityoffountains.Data.menuItemDessert2;
import com.example.cityoffountains.Data.menuItemDessert3;
import com.example.cityoffountains.Data.menuItemDessert4;
import com.example.cityoffountains.Model.menuItemModel;
import com.example.cityoffountains.R;

import java.util.ArrayList;
import java.util.List;

public class MenuItemActivity extends AppCompatActivity {

    public static ArrayList<menuItemModel> data;
    public static String usernameText;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    public static Integer menu;
    public static Integer restaurant;
    public static Integer menuItem;

    private static final String EXTRA_MENU_ID = "com.example.cityoffountains.menu_id";
    private static final String EXTRA_MENU_ITEM_ID = "com.example.cityoffountains.menu_item_id";
    private static final String EXTRA_RESTAURANT_ID = "com.example.cityoffountains.restaurant_id";
    public static final String EXTRA_USERNAME = "com.example.cityoffountains.username";

    public static Intent newIntent(Context packageContext, Integer menuID, Integer RestaurantID, String username) {
        Intent intent = new Intent(packageContext, MenuItemActivity.class);
        intent.putExtra(EXTRA_MENU_ID, menuID);
        intent.putExtra(EXTRA_RESTAURANT_ID, RestaurantID);
        intent.putExtra(EXTRA_USERNAME, username);
        usernameText = username;
        menu = menuID;
        restaurant = RestaurantID;
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item_recyclerview);

        mRecyclerView = findViewById(R.id.menu_item_recycler_view_id);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(restaurant == 1 && menu == 1) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink1.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDrink1.ids[i],
                        menuItemDrink1.drinkNames[i],
                        menuItemDrink1.images[i]
                ));
            }
        } else if(restaurant == 2 && menu == 1) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink2.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDrink2.ids[i],
                        menuItemDrink2.drinkNames[i],
                        menuItemDrink2.images[i]
                ));
            }
        } else if(restaurant == 3 && menu == 1) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink3.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDrink3.ids[i],
                        menuItemDrink3.drinkNames[i],
                        menuItemDrink3.images[i]
                ));
            }
        } else if(restaurant == 4 && menu == 1) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink4.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDrink4.ids[i],
                        menuItemDrink4.drinkNames[i],
                        menuItemDrink4.images[i]
                ));
            }
        } else if(restaurant == 1 && menu == 2) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink4.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemLunch1.ids[i],
                        menuItemLunch1.drinkNames[i],
                        menuItemLunch1.images[i]
                ));
            }
        } else if(restaurant == 2 && menu == 2) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink4.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemLunch2.ids[i],
                        menuItemLunch2.drinkNames[i],
                        menuItemLunch2.images[i]
                ));
            }
        } else if(restaurant == 3 && menu == 2) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink4.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemLunch3.ids[i],
                        menuItemLunch3.drinkNames[i],
                        menuItemLunch3.images[i]
                ));
            }
        } else if(restaurant == 4 && menu == 2) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDrink4.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemLunch4.ids[i],
                        menuItemLunch4.drinkNames[i],
                        menuItemLunch4.images[i]
                ));
            }
        } else if(restaurant == 1 && menu == 3) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemLunch1.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDinner1.ids[i],
                        menuItemDinner1.drinkNames[i],
                        menuItemDinner1.images[i]
                ));
            }
        } else if(restaurant == 2 && menu == 3) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemLunch1.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDinner2.ids[i],
                        menuItemDinner2.drinkNames[i],
                        menuItemDinner2.images[i]
                ));
            }
        } else if(restaurant == 3 && menu == 3) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemLunch1.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDinner3.ids[i],
                        menuItemDinner3.drinkNames[i],
                        menuItemDinner3.images[i]
                ));
            }
        } else if(restaurant == 4 && menu == 3) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemLunch1.drinkNames.length; i++) {
                data.add(new menuItemModel(
                        menuItemDinner4.ids[i],
                        menuItemDinner4.drinkNames[i],
                        menuItemDinner4.images[i]
                ));
            }
        } else if(restaurant == 1 && menu == 4) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDessert1.names.length; i++) {
                data.add(new menuItemModel(
                        menuItemDessert1.ids[i],
                        menuItemDessert1.names[i],
                        menuItemDessert1.images[i]
                ));
            }
        } else if(restaurant == 2 && menu == 4) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDessert2.names.length; i++) {
                data.add(new menuItemModel(
                        menuItemDessert2.ids[i],
                        menuItemDessert2.names[i],
                        menuItemDessert2.images[i]
                ));
            }
        } else if(restaurant == 3 && menu == 4) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDessert3.names.length; i++) {
                data.add(new menuItemModel(
                        menuItemDessert3.ids[i],
                        menuItemDessert3.names[i],
                        menuItemDessert3.images[i]
                ));            }
        } else if(restaurant == 4 && menu == 4) {
            data = new ArrayList<>();
            for(int i = 0; i < menuItemDessert4.names.length; i++) {
                data.add(new menuItemModel(
                        menuItemDessert4.ids[i],
                        menuItemDessert4.names[i],
                        menuItemDessert4.images[i]
                ));
            }
        }

        if(mAdapter == null) {
            mAdapter = new Adapter(data);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private menuItemModel mMenuItemModel;
        private TextView mTextView;
        private ImageView mImageView;

        public void bind(menuItemModel item) {
            mMenuItemModel = item;
            mTextView.setText(mMenuItemModel.getName());
            mImageView.setImageResource(mMenuItemModel.getImage());
        }

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.menu_item_list, parent, false));
            itemView.setOnClickListener(this);

            mTextView = itemView.findViewById(R.id.menu_item_text_view);
            mImageView = itemView.findViewById(R.id.menu_item_image_view);
        }

        @Override
        public void onClick(View view) {
            Intent intent = MenuItemDetailActivity.newIntent(MenuItemActivity.this, mMenuItemModel.getId(), menu, restaurant, usernameText);
            Bundle args = new Bundle();
            menuItem = mMenuItemModel.getId();
            args.putSerializable(EXTRA_RESTAURANT_ID, restaurant);
            args.putSerializable(EXTRA_MENU_ID, menu);
            args.putSerializable(EXTRA_USERNAME, usernameText);
            args.putSerializable(EXTRA_MENU_ITEM_ID, menuItem);
            startActivity(intent, args);
        }
    }

    private class Adapter extends RecyclerView.Adapter<Holder> {

        public List<menuItemModel> data;

        public Adapter(List<menuItemModel> drinks) {
            data = drinks;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MenuItemActivity.this);

            return new Holder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            menuItemModel item = data.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
