package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    public void registerUser(String username, String plainTextPassword, String role) throws SQLException {
        String salt = Password.generateSalt();
        String hashedPassword = Password.hashPassword(plainTextPassword, salt);

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password_hash, salt, role) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, salt);
            stmt.setString(4, role);
            stmt.executeUpdate();
        }
    }

    public boolean authenticateUser(int userId, String plainTextPassword) throws SQLException {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT password_hash, salt FROM users WHERE id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                String salt = rs.getString("salt");
                return Password.verifyPassword(plainTextPassword, salt, storedHash);
            } else {
                return false;
            }
        }
    }

    public String getUserRole(int userId) throws SQLException {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            } else {
                throw new SQLException("User not found.");
            }
        }
    }
}
