package com.example.cityoffountains.Model;

public class Courier {
    private String vehicle;
    private String age;

    public Courier(String vehicle, String age) {
        this.vehicle = vehicle;
        this.age = age;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getAge() {
        return age;
    }
}
