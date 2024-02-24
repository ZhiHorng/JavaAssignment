/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.javaassignment1.gui;

import com.mycompany.javaassignment1.Session;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


/**
 *
 * @author godch
 */
public class DeleteModifySaleOrder extends javax.swing.JFrame {
    private JComboBox<String> customerComboBox;
    private JComboBox<String> productComboBox;
    /**
     * Creates new form DeleteModifySaleOrder
     */
    public DeleteModifySaleOrder() {
        initComponents();
        setSaleInfoTable();
        loadCustomerNames();
        loadProductNames();
        replaceTextFieldsWithComboBoxes();
    }
    public class CustomTableModel extends DefaultTableModel {

        private final boolean[] editableColumns; // Array to store editable status of each column

        // cpnstructor to initialize the model with column names and data
        public CustomTableModel(Object[][] data, Object[] columnNames, boolean[] editableColumns) {
            super(data, columnNames);
            this.editableColumns = editableColumns;
        }

        // pverroide isCellEditable method to specify which cells are editable
        @Override
        public boolean isCellEditable(int row, int column) {
            return editableColumns[column]; // Return true if the cell is editable, false otherwise
        }
    }    
    private void setSaleInfoTable(){
        Session session = Session.getInstance();
        String salesPersonName = session.getUsername();
        // Read sales data from the sales text file

        // define column names and which column can be edited
        Object[] columnNames = {"SaleID", "ProductID", "ProductName", "Category", "Type","Price", "Quantity", "State", "Date","SalesPersonName", "CustomerName"};
        boolean[] editableColumns = {false, false, true, false, false, false, true, false, true, false, true}; // Specify which columns are editable

        // Use custom table model and set editable status for each column
        CustomTableModel model = new CustomTableModel(new Object[][]{}, columnNames, editableColumns);
        saleInfoTable.setModel(model);

        try (BufferedReader reader = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 9 && fields[9].trim().equalsIgnoreCase(salesPersonName) && fields[7].trim().equalsIgnoreCase("Unapproved"))  {
                    Object[] row = {fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9], fields[10]};
                    model.addRow(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading sales data.", "Error", JOptionPane.ERROR_MESSAGE);
        }       
    }

    private boolean removeSaleFromTxtFile(String saleID) {
        String fileName = "sales.txt";
        try {
            // read all lines from the file into a list
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            // remove the line with the matching sale ID
            lines.removeIf(line -> line.startsWith(saleID + ","));

            // write the updated list back to the file
            Files.write(Paths.get(fileName), lines);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; 
        }
    }   

    private void updateStockQuantity(String productID, int quantity) {
        String fileName = "product.txt";
        try {
            // read all lines from the file into a list
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            // fine matching line
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");
                if (fields.length > 0 && fields[0].trim().equals(productID)) {
                    // update stock quantity
                    int currentStock = Integer.parseInt(fields[6].trim());
                    int updatedStock = currentStock + quantity;
                    fields[6] = String.valueOf(updatedStock);
                    lines.set(i, String.join(",", fields));
                    break; 
                }
            }

            // Write the updated list back to the file
            Files.write(Paths.get(fileName), lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load customer names from file
    private void loadCustomerNames() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("customer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                model.addElement(fields[1]); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerComboBox = new JComboBox<>(model);
    }

    // Method to load product names from file
    private void loadProductNames() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                model.addElement(fields[1]); // Assuming product name is at index 1
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productComboBox = new JComboBox<>(model);
    }

    // Method to replace text fields with combo boxes in the table
    private void replaceTextFieldsWithComboBoxes() {
        saleInfoTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(productComboBox)); // Assuming ProductName column is at index 2
        saleInfoTable.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(customerComboBox)); // Assuming CustomerName column is at index 10
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        saleInfoTable = new javax.swing.JTable();
        Delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        modify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        saleInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        saleInfoTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        saleInfoTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(saleInfoTable);
        saleInfoTable.getAccessibleContext().setAccessibleName("");
        saleInfoTable.getAccessibleContext().setAccessibleDescription("");
        saleInfoTable.getAccessibleContext().setAccessibleParent(jScrollPane1);

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jLabel1.setText("Modify Or Delete Sale Order");

        modify.setText("Modify");
        modify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modifyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modify)
                        .addGap(18, 18, 18)
                        .addComponent(Delete))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete)
                    .addComponent(modify))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // get the index of the selected row
        int selectedRow = saleInfoTable.getSelectedRow();
        if (selectedRow == -1) {//if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // get the sale ID and quantity of the selected row
        String saleID = saleInfoTable.getValueAt(selectedRow, 0).toString();
        String productID = saleInfoTable.getValueAt(selectedRow, 1).toString();
        int quantity = Integer.parseInt(saleInfoTable.getValueAt(selectedRow, 6).toString());

        // remove the row from the table
        DefaultTableModel model = (DefaultTableModel) saleInfoTable.getModel();
        model.removeRow(selectedRow);

        // remove the corresponding data from the "sales.txt" file
        if (removeSaleFromTxtFile(saleID)) {
            // Update stock quantity in product.txt
            updateStockQuantity(productID, quantity);
            JOptionPane.showMessageDialog(this, "Sale order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error deleting sale order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void modifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifyMouseClicked
        int selectedRow = saleInfoTable.getSelectedRow();
        if (selectedRow != -1) {
            // Retrieve data from the selected row
            String saleID = saleInfoTable.getValueAt(selectedRow, 0).toString();
            String productID = saleInfoTable.getValueAt(selectedRow, 1).toString();
            String productName = saleInfoTable.getValueAt(selectedRow, 2).toString();
            String category = saleInfoTable.getValueAt(selectedRow, 3).toString();
            String type = saleInfoTable.getValueAt(selectedRow, 4).toString();
            double unitPrice = Double.parseDouble(saleInfoTable.getValueAt(selectedRow, 5).toString());
            int previousQuantity = Integer.parseInt(saleInfoTable.getValueAt(selectedRow, 6).toString());
            String state = saleInfoTable.getValueAt(selectedRow, 7).toString();
            String date = saleInfoTable.getValueAt(selectedRow, 8).toString();
            String salesPersonName = saleInfoTable.getValueAt(selectedRow, 9).toString();
            String customerName = saleInfoTable.getValueAt(selectedRow, 10).toString();
            System.out.println("Selected Sale ID: " + saleID);
            System.out.println("Selected Product ID: " + productID);
            System.out.println("Selected Product Name: " + productName);            

            // Prompt the user for the new quantity
            String newQuantityStr = JOptionPane.showInputDialog(this, "Enter new quantity:", "Modify Quantity", JOptionPane.QUESTION_MESSAGE);
            if (newQuantityStr == null) {
                // user closed the window or no quantity is set
                return;
            }

            try {
                int newQuantity = Integer.parseInt(newQuantityStr);

                // calculate the quantity difference
                int quantityDifference = newQuantity - previousQuantity;

                // update data files
                try {
                    // Read all lines from the sales.txt file into a list
                    List<String> lines = Files.readAllLines(Paths.get("sales.txt"));

                    // Find and update the line with the matching sale ID
                    for (int i = 0; i < lines.size(); i++) {
                        String line = lines.get(i);
                        String[] fields = line.split(",");
                        if (fields.length > 0 && fields[0].trim().equals(saleID) && fields[1].trim().equals(productID)) {
                            // Update the line with the modified data
                            String updatedLine = String.join(",", saleID, productID, productName, category, type, String.valueOf(unitPrice), String.valueOf(newQuantity), state, date, salesPersonName, customerName);
                            lines.set(i, updatedLine);
                            break; 
                        }
                    }

                    Files.write(Paths.get("sales.txt"), lines);

                    // Update stock quantity in product.txt
                    updateStockQuantity(productID, quantityDifference);

                    JOptionPane.showMessageDialog(this, "Sales Order Modified!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modifyMouseClicked

    
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
            java.util.logging.Logger.getLogger(DeleteModifySaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteModifySaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteModifySaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteModifySaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteModifySaleOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modify;
    private javax.swing.JTable saleInfoTable;
    // End of variables declaration//GEN-END:variables
}
