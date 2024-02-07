/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

/**
 *
 * @author godch
 */
// Officer class extending User
class Officer extends User {
    public Officer(String userID, String username, String userPassword, String role, String status, int age, String email, String phoneNumber) {
        super(userID, username, userPassword, role, status, age, email, phoneNumber);
    }
}
