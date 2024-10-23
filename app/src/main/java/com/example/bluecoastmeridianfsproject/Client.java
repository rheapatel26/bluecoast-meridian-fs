package com.example.bluecoastmeridianfsproject;

import java.util.List;

public class Client {
    private String firstname;
    private List<Long> holdings;
    private List<String> stocknames;
    private List<Long> bought;

    public Client() {
        // Default constructor required for calls to DataSnapshot.getValue(Client.class)
    }

    public Client(String firstname, List<Long> holdings, List<String> stocknames, List<Long> bought) {
        this.firstname = firstname;
        this.holdings = holdings;
        this.stocknames = stocknames;
        this.bought = bought;
    }

    // Getters
    public String getFirstname() { return firstname; }
    public List<Long> getHoldings() { return holdings; }
    public List<String> getStocknames() { return stocknames; }
    public List<Long> getBought() { return bought; }
}
