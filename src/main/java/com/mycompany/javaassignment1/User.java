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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private String userID;
    private String username;
    private String userPassword;
    private String role;
    private String status;
    private int age;
    private String email;
    private String phoneNumber;

    // Constructor
    public User(String userID, String username, String userPassword, String role, String status, int age, String email, String phoneNumber) {
        this.userID = userID;
        this.username = username;
        this.userPassword = userPassword;
        this.role = role;
        this.status = status;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Method for login handling
    public boolean login(String username, String password) {
        // Check if provided username and password match the user's credentials
        return this.username.equals(username) && this.userPassword.equals(password);
    }

    // Getter methods
    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String viewProfile() {
        // search for user details based on username
        Session session = Session.getInstance();
        String usernameToSearch = session.getUsername();
        StringBuilder userProfile = new StringBuilder();

        Map<String, User> userDetails = getUserDetails();

        if (userDetails.containsKey(usernameToSearch)) {
            User user = userDetails.get(usernameToSearch);
            // construct the user profile string
            userProfile.append("UserID: ").append(user.getUserID()).append("\n");
            userProfile.append("Username: ").append(user.getUsername()).append("\n");
            userProfile.append("Role: ").append(user.getRole()).append("\n");
            userProfile.append("Status: ").append(user.getStatus()).append("\n");
            userProfile.append("Age: ").append(user.getAge()).append("\n");
            userProfile.append("Email: ").append(user.getEmail()).append("\n");
            userProfile.append("Phone Number: ").append(user.getPhoneNumber()).append("\n");
        } else {
            userProfile.append("User not found.");
        }

        String userProfileString = userProfile.toString();
        return userProfileString; 
    }

    // method to get user details 
    private Map<String, User> getUserDetails() {
        Map<String, User> userDetails = null;
        try {
            userDetails = User.readUserDetailsFromFile("user_details.txt");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unknown Error when accessing user details");
        }
        return userDetails;
    }
    
    //read user detail from file method
    public static Map<String, User> readUserDetailsFromFile(String filePath) throws IOException {
        Map<String, User> userDetails = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    // skip the first row 
                    continue;
                }
                String[] parts = line.split(",");
                String userID = parts[0].trim();
                String username = parts[1].trim();
                String password = parts[2].trim(); 
                String role = parts[3].trim();
                String status = parts[4].trim();
                int age = Integer.parseInt(parts[5].trim());
                String email = parts[6].trim();
                String phoneNumber = parts[7].trim();

                User user;
                switch (role) {
                    case "Administrator":
                        user = new Administrator(userID, username, password, role, status, age, email, phoneNumber);
                        break;
                    case "Officer":
                        user = new Officer(userID, username, password, role, status, age, email, phoneNumber);
                        break;
                    case "Salesperson":
                        user = new Salesperson(userID, username, password, role, status, age, email, phoneNumber);
                        break;
                    default:
                        // handle unknown role
                        throw new IllegalArgumentException("Unknown role: " + role);
                }

                userDetails.put(username, user);
            }
        }
        return userDetails;
    }
}
