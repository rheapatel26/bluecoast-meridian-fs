package com.example.bluecoastmeridianfsproject;

import java.util.List;

public class Client {
    private String firstname;
    private List<Integer> bought;
    private List<Integer> current; // Add this list to hold current prices
    private List<Integer> holdings;
    private List<String> stocknames;

    // Constructor, getters, and setters

    public String getFirstname() {
        return firstname;
    }

    public List<Integer> getBought() {
        return bought;
    }

    public List<Integer> getCurrent() {
        return current; // Ensure this method exists
    }

    public List<Integer> getHoldings() {
        return holdings;
    }

    public List<String> getStocknames() {
        return stocknames;
    }
}
