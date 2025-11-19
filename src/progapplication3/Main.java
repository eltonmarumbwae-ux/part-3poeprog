/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progapplication3;

import javax.swing.*;

/**
 *
 * @author RC_Student_Lab
 */
public class Main {

    public static void main(String[] args) {
        // Registration (could be pre-populated for tests)
        Registration reg = new Registration("Elton", "alice01", "password123");
        Login login = new Login(reg);

        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");

        if (login.checkUserDetails(username, password)) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            MessageManager manager = new MessageManager();
            manager.startMessaging();
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed!");
        }
    }
}
