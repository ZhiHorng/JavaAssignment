/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

/**
 *
 * @author godch
 */
public class Session {
    private static Session instance;
    private String userID;
    private String username;
    private String password;
    private String role;
    private String status;
    private int age;
    private String email;
    private String phoneNumber;

    // Private constructor to prevent instantiation from outside
    public Session() {}

    // instance of Session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to clear session data (logout)
    public void clearSession() {
        this.userID = null;
        this.username = null;
        this.password = null;
        this.role = null;
        this.status = null;
        this.age = 0;
        this.email = null;
        this.phoneNumber = null;
    }
}


