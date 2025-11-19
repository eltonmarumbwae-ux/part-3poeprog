/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progapplication3;

/**
 *
 * @author RC_Student_Lab
 */


public class Registration {
    private String name;
    private String userName;
    private String password;

    public Registration(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getName() { return name; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
}
