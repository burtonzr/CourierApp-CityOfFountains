package com.example.cityoffountains.Model;

public class menuModel {
    private int id;
    private String name;
    private int image;

    public menuModel(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

}
