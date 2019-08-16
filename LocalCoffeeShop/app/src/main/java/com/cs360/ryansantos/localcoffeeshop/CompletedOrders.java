package com.cs360.ryansantos.localcoffeeshop;

import java.util.ArrayList;

public class CompletedOrders {

    private ArrayList<Order> var_order;
    private double var_total;

    public CompletedOrders() {}

    public CompletedOrders(ArrayList<Order> order, double total){
        this.var_order = order;
        this.var_total = total;
    }

    public void setOrder(ArrayList<Order> order) {
        this.var_order = order;
    }

    public void setTotal(int total) {
        this.var_total = total;
    }

    public ArrayList<Order> getOrder() {
        return this.var_order;
    }

    public double getTotal() {
        return this.var_total;
    }
}
