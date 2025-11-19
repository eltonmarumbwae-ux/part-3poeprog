/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progapplication3;

/**
 *
 * @author RC_Student_Lab
 */




public class Login {
    private Registration registration;

    public Login(Registration registration) {
        this.registration = registration;
    }

    public boolean checkUserDetails(String username, String password) {
        return registration.getUserName().equals(username) &&
               registration.getPassword().equals(password);
    }
}
