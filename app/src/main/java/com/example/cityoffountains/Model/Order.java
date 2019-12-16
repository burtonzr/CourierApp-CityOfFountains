package com.example.cityoffountains.Model;

public class Order {
    public String CustomerID;
    public String deliveryAddress;
    public String deliveryPersonnel;
    public String totalAmount;
    public String paymentMethod;
    public String cardNumber;
    public String Status;

    public Order(String CustomerID, String deliveryAddress, String deliveryPersonnel, String totalAmount, String paymentMethod, String cardNumber, String Status) {
        this.CustomerID         = CustomerID;
        this.deliveryAddress    = deliveryAddress;
        this.deliveryPersonnel  = deliveryPersonnel;
        this.totalAmount        = totalAmount;
        this.paymentMethod      = paymentMethod;
        this.cardNumber         = cardNumber;
        this.Status             = Status;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getdeliveryAddress() {
        return deliveryAddress;
    }

    public String getdeliveryPersonnel() {
        return deliveryPersonnel;
    }

    public String gettotalAmount() {
        return totalAmount;
    }

    public String getpaymentMethod() {
        return paymentMethod;
    }

    public String getcardNumber() {
        return cardNumber;
    }

    public String getStatus() {
        return Status;
    }
}
