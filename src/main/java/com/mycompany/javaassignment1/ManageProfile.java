/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManageProfile {
    public static void updatePassword(String userId, String newPassword) {
        // Read the user_details.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("user_details.txt"))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // Split the line into fields
                String[] fields = line.split(", ");
                // Check if the user ID matches
                if (fields.length > 0 && fields[0].equals(userId)) {
                    // Update the password field
                    fields[2] = newPassword;
                    // Reconstruct the line with updated password
                    line = String.join(", ", fields);
                }
                // Append the line (either updated or original) to the content
                updatedContent.append(line).append("\n");
            }
            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt"))) {
                writer.write(updatedContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void editUserDetails(String userId, String[] newFields) {
        // Read the user_details.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("user_details.txt"))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // Split the line into fields
                String[] fields = line.split(", ");
                // Check if the user ID matches
                if (fields.length > 0 && fields[0].equals(userId)) {
                    // Update all user fields
                    for (int i = 1; i < fields.length && i < newFields.length; i++) {
                        fields[i] = newFields[i];
                    }
                    // Reconstruct the line with updated fields
                    line = String.join(", ", fields);
                }
                // Append the line (either updated or original) to the content
                updatedContent.append(line).append("\n");
            }
            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt"))) {
                writer.write(updatedContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
