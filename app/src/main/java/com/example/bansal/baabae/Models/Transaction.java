package com.example.bansal.baabae.Models;

import java.io.Serializable;

public class Transaction implements Serializable {
private String transactionID;
private String transactionTimeStamp;
private OrderSummary summary;

public Transaction() {}
public Transaction(String id, String transactionTimeStamp, OrderSummary s){
    this.transactionID=id;
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

    public OrderSummary getSummary() {
        return summary;
    }

    public void setSummary(OrderSummary summary) {
        this.summary = summary;
    }
}
