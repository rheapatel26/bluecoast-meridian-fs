package com.example.bluecoastmeridianfsproject;

public class person {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String firstname;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String stocksnames;

    // Variable to store data corresponding
    // to age keyword in database
    private String holdings;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {}

    // Getter and setter method
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getStocksnames()
    {
        return stocksnames;
    }
    public void setStocksnames(String stocksnames)
    {
        this.stocksnames = stocksnames;
    }
    public String getHoldings()
    {
        return holdings;
    }
    public void setHoldings(String holdings)
    {
        this.holdings = holdings;
    }
}
