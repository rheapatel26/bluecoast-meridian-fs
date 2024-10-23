package com.example.bluecoastmeridianfsproject;

public class Client {
    private String firstname;
    private String holdings;
    private String stocknames;

    public Client() {
        // Default constructor required for calls to DataSnapshot.getValue(Client.class)
    }

    public Client(String firstname, String holdings, String stocknames) {
        this.firstname = firstname;
        this.holdings = holdings;
        this.stocknames = stocknames;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getHoldings() {
        return holdings;
    }

    public String getStocknames() {
        return stocknames;
    }
}
