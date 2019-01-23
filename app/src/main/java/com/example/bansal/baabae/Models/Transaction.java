package com.example.bansal.baabae.Models;

import java.io.Serializable;

public class Transaction implements Serializable {
private String transactionID;
private String transactionTimeStamp;
private  String address;
private OrderSummary summary;

public Transaction() {}
public Transaction(String id, String transactionTimeStamp,String address, OrderSummary s){
    this.transactionID=id;
    this.address=address;
    this.transactionTimeStamp=transactionTimeStamp;
    this.summary=s;
}
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(String transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderSummary getSummary() {
        return summary;
    }

    public void setSummary(OrderSummary summary) {
        this.summary = summary;
    }
}
