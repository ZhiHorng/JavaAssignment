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
        // read user details text file
        try (BufferedReader reader = new BufferedReader(new FileReader("user_details.txt"))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // split lines based on comma into part
                String[] fields = line.split(", ");
                // if user id match to the session id
                if (fields.length > 0 && fields[0].equals(userId)) {
                    //update password
                    fields[2] = newPassword;
                    // reconstruct the line with new password
                    line = String.join(", ", fields);
                }
                // append the line back to the text file
                updatedContent.append(line).append("\n");
            }
            // write updated content to the text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt"))) {
                writer.write(updatedContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void editUserDetails(String userId, String[] newFields) {
        // read user details text file
        try (BufferedReader reader = new BufferedReader(new FileReader("user_details.txt"))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // split lines based on comma into part
                String[] fields = line.split(", ");
                // if user id match to the session id
                if (fields.length > 0 && fields[0].equals(userId)) {
                    // update user field
                    for (int i = 1; i < fields.length && i < newFields.length; i++) {
                        fields[i] = newFields[i];
                    }
                    // reconstruct the line with updated fields
                    line = String.join(", ", fields);
                }
                // Append to the content
                updatedContent.append(line).append("\n");
            }
            // Write back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt"))) {
                writer.write(updatedContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
