/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.javaassignment1.gui;
import com.mycompany.javaassignment1.Session;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author godch
 */
public class CreateSaleOrder extends javax.swing.JFrame {
    private List<String[]> productList = new ArrayList<>();
    /**
     * Creates new form CreateSaleOrder
     */
    public CreateSaleOrder() {
        initComponents();
        populateCustomerNames();
        populateProductNames();
    }
    
    private void populateCustomerNames() {
        try (BufferedReader reader = new BufferedReader(new FileReader("customer.txt"))) {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(",");
                // Add the customer name to the model
                model.addElement(parts[1]); 
            }
            // Set the model to the combobox
            customerField.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error has occurred when fetching customer name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateProductNames() {
        try (BufferedReader reader = new BufferedReader(new FileReader("product.txt"))) {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(",");
                // Add the product name to the model
                model.addElement(parts[1]); // Assuming the product name is at index 1
            }
            // Set the model to the combobox
            productSelection.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error has occurred when fetching product name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String[] getProductData(String productName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(productName)) {
                    return parts;
                }
            }
            throw new IllegalArgumentException("Product not found: " + productName);
        }
    }
    
    private void updateProductStock(String productName, int stockQuantity) throws IOException {
        // Read all product data, update stock quantity, and write back to file
        BufferedReader reader = new BufferedReader(new FileReader("product.txt"));
        String line;
        StringBuilder updatedFileContent = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[1].equals(productName)) {
                parts[6] = String.valueOf(stockQuantity); // Update stock quantity
            }
            updatedFileContent.append(String.join(",", parts)).append("\n");
        }
        reader.close();

        PrintWriter writer = new PrintWriter(new FileWriter("product.txt"));
        writer.print(updatedFileContent.toString());
        writer.close();
    }

    private int getNextSaleID() throws IOException {
        int maxID = 0;
        boolean firstRow = true; // Flag to skip the first row
        try (BufferedReader reader = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;
                    continue; // Skip the first row
                }
                String[] parts = line.split(",");
                int saleID = Integer.parseInt(parts[0]);
                if (saleID > maxID) {
                    maxID = saleID;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxID + 1;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        customerField = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productSelection = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel5 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        addSale = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Create Sale Order Quotation");

        jLabel2.setText("Customer Name:");

        jLabel3.setText("Product");

        jLabel4.setText("Quantity:");

        quantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Date:");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        addSale.setText("Add");
        addSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4)
                            .addComponent(addSale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(customerField, 0, 190, Short.MAX_VALUE)
                                    .addComponent(productSelection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(quantityField))
                                .addGap(45, 45, 45)
                                .addComponent(jLabel5))
                            .addComponent(submitButton))
                        .addGap(18, 18, 18)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(productSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitButton)
                            .addComponent(addSale)))
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        try {
            String customerName = (String) customerField.getSelectedItem();
            String productName = (String) productSelection.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());
            String salesPersonName = Session.getInstance().getUsername();
            // Get the selected date from the calendar
            Calendar selectedCalendar = jCalendar1.getCalendar();
            Date selectedDate = null;
            if (selectedCalendar != null) {
                selectedDate = selectedCalendar.getTime();
            }

            // Format the date as needed (e.g., to match the format in the files)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = null;
            if (selectedDate != null) {
                formattedDate = dateFormat.format(selectedDate);
            }
            
            String[] productData = getProductData(productName);
            
            int saleID = getNextSaleID();
            int stockQuantity = Integer.parseInt(productData[6]); // Assuming stock quantity is at index 6
            if (stockQuantity < quantity) {
                JOptionPane.showMessageDialog(null, "Not enough stock available for the selected product", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (String[] product : productList) {
                String saleQuotation = String.format("%d,%s,%s,%s,%s,%s,%s,Unapproved,%s,%s,%s",
                        saleID , product[0], product[1], product[2], product[3], product[4], product[6], formattedDate, salesPersonName, customerName);
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales.txt", true))) {
                    writer.write(saleQuotation);
                    writer.newLine();
                }
            }
            
            JOptionPane.showMessageDialog(null, "Sale quotations created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            productList.clear(); // Clear the product list after submission
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while creating the sale quotations", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void quantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityFieldActionPerformed

    private void addSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSaleActionPerformed
        try {
            String productName = (String) productSelection.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());
            
            // Validate quantity
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String[] productData = getProductData(productName);
            int stockQuantity = Integer.parseInt(productData[6]); // Assuming stock quantity is at index 6
            if (stockQuantity < quantity) {
                JOptionPane.showMessageDialog(null, "Not enough stock available for the selected product", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Reduce stock quantity
            stockQuantity -= quantity;
            updateProductStock(productName, stockQuantity);
            
            // Add product details to the list
            productList.add(new String[]{productData[0], productName, productData[2], productData[3], productData[4], productData[5], String.valueOf(quantity)});
            
            JOptionPane.showMessageDialog(null, "Product added to the list", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while adding the product", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addSaleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateSaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateSaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateSaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateSaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateSaleOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSale;
    private javax.swing.JComboBox<String> customerField;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> productSelection;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}