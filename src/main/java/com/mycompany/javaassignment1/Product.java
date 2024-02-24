/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

/**
 *
 * @author godch
 */
public class Product {
    private int productID;
    private String productName;
    private String productCategory;
    private double price;
    private String description;
    private int quantity;

    // Constructor
    public Product(int productID, String productName, String productCategory, double price, String description, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    // Getters and setters (including setStockQuantity)
    // Note: I've added the missing getters and setters as well
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setStockQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
