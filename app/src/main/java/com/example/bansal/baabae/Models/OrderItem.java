package com.example.bansal.baabae.Models;

public class OrderItem {

    private String name;
    private String price;
    private String quantity;

    public OrderItem(){

    }
    public OrderItem(String name, String price, String quant){
        this.name=name;
        this.price=price;
        this.quantity=quant;
    }
    public OrderItem(String name) {
    this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
