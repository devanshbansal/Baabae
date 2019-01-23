package com.example.bansal.baabae.Models;

import java.util.ArrayList;

public class OrderSummary {
    private String address;
    private ArrayList<OrderItem> items;
    public OrderSummary(){
        items=new ArrayList<OrderItem>();
    }
    public OrderSummary(String address, ArrayList<OrderItem> r){
        this.address = address;
        this.items=r;
}
public OrderSummary(ArrayList<OrderItem> r){
        this.items=r;
}
    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
