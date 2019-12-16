package com.example.cityoffountains.Model;

public class Cart {
    private int OrderID;
    private String ProductName;
    private String ProductPrice;
    private String Quantity;
    private String CustomerID;
    private int RestaurantID;
    private String Status;

    public Cart(int OrderID, String ProductName, String ProductPrice, String Quantity, String CustomerID, int RestaurantID, String Status) {
        this.OrderID         = OrderID;
        this.ProductName     = ProductName;
        this.ProductPrice    = ProductPrice;
        this.Quantity        = Quantity;
        this.CustomerID      = CustomerID;
        this.RestaurantID    = RestaurantID;
        this.Status          = Status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public int getRestaurantID() {
        return RestaurantID;
    }

    public String getStatus() {
        return Status;
    }
}
