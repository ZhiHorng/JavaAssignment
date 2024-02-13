/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

/**
 *
 * @author godch
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleOrderQuotation {
    private int quotationID;
    private Date creationDate;
    private List<Product> products;

    // Constructor
    public SaleOrderQuotation(int quotationID, Date creationDate, List<Product> products) {
        this.quotationID = quotationID;
        this.creationDate = creationDate;
        this.products = products;
    }

    // Getter and setter methods

    // Method to create a sale order based on the quotation
    public int createSaleOrder() {
        List<Product> updatedProducts = new ArrayList<>();
        int resultCode = 0; // Default success status code

        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int productID = Integer.parseInt(data[0]);
                String productName = data[1];
                String productCategory = data[2];  // Assuming category is at index 2
                double price = Double.parseDouble(data[4]);  // Assuming price is at index 4
                String description = data[5];  // Assuming description is at index 5
                int stockQuantity = Integer.parseInt(data[6]);  // Assuming stock quantity is at index 6

                for (Product product : products) {
                    if (product.getProductName().equals(productName)) {
                        if (stockQuantity >= product.getQuantity()) {
                            // Deduct quantity from stock count
                            stockQuantity -= product.getQuantity();
                            product.setStockQuantity(stockQuantity); // Update product quantity
                            resultCode = 1; // Success status code
                        } else {
                            resultCode = -1; // Insufficient stock status code
                        }
                        break;
                    }
                }
                updatedProducts.add(new Product(productID, productName, productCategory, price, description, stockQuantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultCode = -2; // Error status code
        }

        // Save updated product information back to the file
        try (FileWriter writer = new FileWriter("products.txt")) {
            for (Product product : updatedProducts) {
                writer.write(product.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultCode = -2; // Error status code
        }

        // Write new sale orders to the sales file
        try (FileWriter writer = new FileWriter("sales.txt", true)) {
            for (Product product : products) {
                writer.write(product.toString() + "\n"); // Assuming product.toString() returns sale order info
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultCode = -2; // Error status code
        }

        return resultCode;
    }

    // Method to remove the quotation
    public void removeQuotation() {
        // Implementation to remove the quotation
    }
}


