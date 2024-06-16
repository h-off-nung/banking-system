package com.bank;

import java.sql.*;
import java.util.Scanner;

public class Service {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authentication userAuth = new Authentication();

        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter your password: ");
        String password = new String(System.console().readPassword());

        try {
            if (userAuth.authenticateUser(userId, password)) {
                String role = userAuth.getUserRole(userId);
                System.out.println("Authentication successful. Your role is: " + role);
                // Proceed based on the role
                if ("ADMIN".equals(role)) {
                    // Admin-specific actions
                } else {
                    // User-specific actions
                }
            } else {
                System.out.println("Authentication failed. Invalid ID or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
