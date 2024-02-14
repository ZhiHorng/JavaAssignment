/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

/**
 *
 * @author godch
 */
import java.util.Date;

public class SaleOrder {
    private int saleID;
    private int productID;
    private String productName;
    private String category;
    private String type;
    private double price;
    private int quantity;
    private String state;
    private Date date;
    private String salesperson;

    // Constructor
    public SaleOrder(int saleID, int productID, String productName, String category, String type, double price, int quantity, String state, Date date, String salesperson) {
        this.saleID = saleID;
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.state = state;
        this.date = date;
        this.salesperson = salesperson;
    }

    // Getters
    public int getSaleID() {
        return saleID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getSalesperson() {
        return salesperson;
    }

    // toString method
    @Override
    public String toString() {
        return saleID + "," + productID + "," + productName + "," + category + "," + type + "," + price + "," + quantity + "," + state + "," + date + "," + salesperson;
    }
}

