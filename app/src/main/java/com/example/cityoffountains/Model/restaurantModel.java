package com.example.cityoffountains.Model;

public class restaurantModel {
    private String restaurantName;
    private int id;
    private int image;

    public restaurantModel(String restaurantName, int id, int image) {
        this.restaurantName = restaurantName;
        this.id = id;
        this.image = image;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
}
