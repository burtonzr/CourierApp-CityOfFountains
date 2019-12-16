package com.example.cityoffountains.Model;

public class menuItemModel {
    private int id;
    private String name;
    private int image;
    private double price;

    public menuItemModel(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
