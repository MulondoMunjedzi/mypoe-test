/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginsystem;

/**
 *
 * @author Mulondo Munjodzi
 */
import java.util.Scanner;

public class LoginSystem {
    
    private String storedFirstName;
    private String storedLastName;
    private String storedUsername;
    private String storedPassword;
    private String storedPhoneNumber;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginSystem user = new LoginSystem();

        System.out.println("== REGISTRATION ==");

        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        // USERNAME LOOP
        String username;
        while (true) {
            System.out.println("Enter username (must contain '_' and max 5 characters):");
            username = scanner.nextLine();

            if (user.isUsernameValid(username)) break;
            else System.out.println("Invalid username. Try again.");
        }

        // PASSWORD LOOP
        String password;
        while (true) {
            System.out.println("Enter password (8+ chars, uppercase, number, special char):");
            password = scanner.nextLine();

            if (user.isPasswordValid(password)) break;
            else System.out.println("Invalid password. Try again.");
        }

        // PHONE LOOP
        String phoneNumber;
        while (true) {
            System.out.println("Enter phone number (+27...):");
            phoneNumber = scanner.nextLine();

            if (user.isPhoneNumberValid(phoneNumber)) break;
            else System.out.println("Invalid phone number. Try again.");
        }

        // REGISTER
        System.out.println(user.saveUserDetails(firstName, lastName, username, password, phoneNumber));

        // LOGIN
        System.out.println("\n== LOGIN ==");

        boolean loginStatus;

        while (true) {

            System.out.println("Enter username:");
            String loginUsername = scanner.nextLine();

            System.out.println("Enter password:");
            String loginPassword = scanner.nextLine();

            loginStatus = user.isLoginSuccessful(loginUsername, loginPassword);

            if (loginStatus) break;
            else System.out.println("Username or password incorrect, try again.\n");
        }

        System.out.println(user.getLoginMessage(loginStatus));
        Message.main(args);
       

        scanner.close();
    }

    // USERNAME CHECK
    public boolean isUsernameValid(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // PASSWORD CHECK
    public boolean isPasswordValid(String password) {

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char x : password.toCharArray()) {
            if (Character.isUpperCase(x)) hasUpper = true;
            if (Character.isDigit(x)) hasNumber = true;
            if (!Character.isLetterOrDigit(x)) hasSpecial = true;
        }

        return password.length() >= 8 && hasUpper && hasNumber && hasSpecial;
    }

    // PHONE CHECK
    public boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("+27") && phoneNumber.length() == 12;
    }

    // REGISTER
    public String saveUserDetails(String firstName, String lastName,
                                  String username, String password,
                                  String phoneNumber) {

        storedFirstName = firstName;
        storedLastName = lastName;
        storedUsername = username;
        storedPassword = password;
        storedPhoneNumber = phoneNumber;

        return "Registration successful for " + firstName + " " + lastName;
    }

    // LOGIN
    public boolean isLoginSuccessful(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // LOGIN MESSAGE
    public String getLoginMessage(boolean loginStatus) {

        if (loginStatus) {
            return "Welcome " + storedFirstName + " " + storedLastName +
                   ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

    
        
        
        
        
        
        
        
        
        
        
        
        
   
