package com.example.bansal.baabae.Models;

import java.util.ArrayList;

public class OrderSummary {
    private ArrayList<OrderItem> items;
    public OrderSummary(){
        items=new ArrayList<OrderItem>();
    }
public OrderSummary(ArrayList<OrderItem> r){
        this.items=r;
}
    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
