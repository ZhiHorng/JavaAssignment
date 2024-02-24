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

    // method to display all worker profiles in the workerProfile table
    public void displayWorkerProfiles(JTable workerProfile) {
        DefaultTableModel model = (DefaultTableModel) workerProfile.getModel();
        model.setRowCount(0); 

        // set column names
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Password", "Role", "Status", "Age", "Email", "Phone Number"});

        try (BufferedReader reader = new BufferedReader(new FileReader("user_details.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(", ");
                // check if the fields in text file have at least 8 part
                if (fields.length >= 8) {
                    //create combo box with two option, active or inactive to allow user to select
                    JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Active", "Inactive"});
                    statusComboBox.setSelectedItem(fields[4]); //set the default value of the combo box based on the field in text

                    //add cell editor to column
                    TableColumn statusColumn = workerProfile.getColumnModel().getColumn(4);
                    statusColumn.setCellEditor(new DefaultCellEditor(statusComboBox));

                    //create combo box with two option, active or inactive to allow user to select
                    JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Salesperson", "Administrator", "Officer"});
                    roleComboBox.setSelectedItem(fields[3]); // //set the default value of the combo box based on the field in text

                    //add cell editor to column
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
            // clear the contents of the file first
            BufferedWriter clearWriter = new BufferedWriter(new FileWriter("user_details.txt", false));
            clearWriter.close();

            // write data back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt", true))) {

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

            JOptionPane.showMessageDialog(workerProfile, "User profiles updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // refresh the table when success
            reloadWorkerProfiles(workerProfile);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(workerProfile, "Error saving user details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reloadWorkerProfiles(JTable workerProfile) {
        // clear the table
        DefaultTableModel model = (DefaultTableModel) workerProfile.getModel();
        model.setRowCount(0);

        displayWorkerProfiles(workerProfile);
    }
}
