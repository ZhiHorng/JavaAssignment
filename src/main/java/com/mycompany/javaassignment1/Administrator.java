/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author godch
 */
// Administrator class extending User

public class Administrator extends User {
    public Administrator(String userID, String username, String userPassword, String role, String status, int age, String email, String phoneNumber) {
        super(userID, username, userPassword, role, status, age, email, phoneNumber);
    }

    // Method to display all worker profiles in the workerProfile table
    public void displayWorkerProfiles(JTable workerProfile) {
        DefaultTableModel model = (DefaultTableModel) workerProfile.getModel();
        model.setRowCount(0); // Clear existing table data

        // Set column names
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Password", "Role", "Status", "Age", "Email", "Phone Number"});

        try (BufferedReader reader = new BufferedReader(new FileReader("user_details.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(", ");
                // Check if the fields array has at least 8 elements (from username to password)
                if (fields.length >= 8) {
                    // Add id, name, password, role, status, age, email, and phone number fields to the table
                    // Create a combobox for the status column with options "Active" and "Inactive"
                    JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Active", "Inactive"});
                    statusComboBox.setSelectedItem(fields[4]); // Set the initial selected item based on the value in the file

                    // Add a cell editor for the status column
                    TableColumn statusColumn = workerProfile.getColumnModel().getColumn(4);
                    statusColumn.setCellEditor(new DefaultCellEditor(statusComboBox));

                    // Create a combobox for the role column with options "Salesperson," "Administrator," and "Officer"
                    JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Salesperson", "Administrator", "Officer"});
                    roleComboBox.setSelectedItem(fields[3]); // Set the initial selected item based on the value in the file

                    // Add a cell editor for the role column
                    TableColumn roleColumn = workerProfile.getColumnModel().getColumn(3);
                    roleColumn.setCellEditor(new DefaultCellEditor(roleComboBox));

                    model.addRow(new Object[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to rewrite data from the table back to user_details.txt
    public void saveWorkerProfiles(JTable workerProfile) {
        DefaultTableModel model = (DefaultTableModel) workerProfile.getModel();

        try {
            // Clear the contents of the file
            BufferedWriter clearWriter = new BufferedWriter(new FileWriter("user_details.txt", false));
            clearWriter.close();

            // Write data to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt", true))) {

                // Write data
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        writer.write(model.getValueAt(row, col).toString());
                        if (col < model.getColumnCount() - 1) {
                            writer.write(", ");
                        } else {
                            writer.write("\n");
                        }
                    }
                }
            }

            // Display success message
            JOptionPane.showMessageDialog(null, "User profiles updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Refresh the table
            reloadWorkerProfiles(workerProfile);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving user details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reloadWorkerProfiles(JTable workerProfile) {
        // Clear the table
        DefaultTableModel model = (DefaultTableModel) workerProfile.getModel();
        model.setRowCount(0);

        // Reload data
        displayWorkerProfiles(workerProfile);
    }
}
