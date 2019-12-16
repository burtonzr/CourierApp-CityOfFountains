package com.example.cityoffountains.Model;

public class Payment {
    private int paymentID;
    private String paymentMethod;
    private String paymentAmount;

    public Payment(int paymentID, String paymentMethod, String paymentAmount) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
