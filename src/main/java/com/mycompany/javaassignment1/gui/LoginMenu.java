/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.javaassignment1.gui;

/**
 *
 * @author godch
 */

import com.mycompany.javaassignment1.User;
import com.mycompany.javaassignment1.Session;
import java.io.IOException;
import java.util.Map;
import javax.swing.JOptionPane;

public class LoginMenu extends javax.swing.JFrame {

    private Map<String, User> userDetails;

    /**
     * Creates new form LoginMenu
     */
    public LoginMenu() {
        initComponents();
        // Load user details from file
        try {
            userDetails = User.readUserDetailsFromFile("user_details.txt");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load user details.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        userPassword = new javax.swing.JPasswordField();
        usernameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Yoyo-Furniture Management System");

        jLabel2.setText("Username: ");

        jLabel3.setText("Password:");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(userPassword))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
    String username = usernameField.getText();
    String password = new String(userPassword.getPassword());

    // Check if username exists
    if (!userDetails.containsKey(username)) {
        JOptionPane.showMessageDialog(this, "Invalid username.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get user object
    User user = userDetails.get(username);
    
    if (user.getStatus().equalsIgnoreCase("Inactive")) {
        JOptionPane.showMessageDialog(this, "Your account is inactive. Please contact the administrator.", "Account Inactive", JOptionPane.ERROR_MESSAGE);
        return;
    }    

    // Check login credentials
    if (user.login(username, password)) {
        JOptionPane.showMessageDialog(this, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Close login menu window
        this.dispose();
        Session session = Session.getInstance();
        session.setUsername(username);
        session.setRole(user.getRole());
        session.setUserID(user.getUserID());
        session.setPassword(password);
        session.setStatus(user.getStatus());

        // Open main menu window based on user's role
        openMainMenu(user.getRole(), username);
    } else {
        JOptionPane.showMessageDialog(this, "Invalid password.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Method to open main menu window based on user's role
    private void openMainMenu(String role, String username) {
        switch (role) {
            case "Administrator":
                AdministratorMenu adminMenu = new AdministratorMenu();
                adminMenu.setWelcomeMessage(username);
                adminMenu.setVisible(true);
                break;
            case "Officer":
                OfficerMenu officerMenu = new OfficerMenu();
                officerMenu.setVisible(true);
                break;
            case "Salesperson":
                SalespersonMenu salesMenu = new SalespersonMenu();
                salesMenu.setWelcomeMessage(username);
                salesMenu.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid user role.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }        
    }//GEN-LAST:event_loginButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField userPassword;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
