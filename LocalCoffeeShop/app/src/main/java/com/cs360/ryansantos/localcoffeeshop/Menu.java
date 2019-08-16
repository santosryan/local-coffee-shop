package com.cs360.ryansantos.localcoffeeshop;

public class Menu {

    //instance variables
    private int var_id;
    private double var_price;
    private String var_name;

    // empty Constructor
    public Menu() {}

    // constructor with all three variables
    public Menu(int itemID, double itemPrice, String itemName){
        this.var_id = itemID;
        this.var_price = itemPrice;
        this.var_name = itemName;
    }

    // contructor without id
    public Menu(double itemPrice, String itemName){
        this.var_price = itemPrice;
        this.var_name = itemName;
    }

    // setters (mutators)
    public void setID(int itemID) { this.var_id = itemID; }

    public void setName(String itemName) { this.var_name = itemName; }

    public void setPrice(double itemPrice) { this.var_price = itemPrice; }

    // getters (accessors)
    public int getID() { return this.var_id; }

    public String getName() { return this.var_name; }

    public double getPrice() { return this.var_price; }

}
