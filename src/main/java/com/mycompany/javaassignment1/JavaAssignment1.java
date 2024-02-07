/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;

import com.mycompany.javaassignment1.gui.LoginMenu;

/**
 *
 * @author godch
 */


public class JavaAssignment1 {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginMenu().setVisible(true);
            }
        });
    }
}
