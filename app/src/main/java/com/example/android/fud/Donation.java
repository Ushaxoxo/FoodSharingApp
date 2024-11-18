package com.example.android.fud;

public class Donation {
    private String name;
    private String dish;
    private int quantity;

    public Donation(String name, String dish, int quantity) {
        this.name = name;
        this.dish = dish;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }
}
