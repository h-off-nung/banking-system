package com.bank;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Database {
    private String url;
    private String user;
    private String password;

    public Database() {
        try {
        Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("database.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAccount(Account account) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO accounts (account_id, balance, owner_name) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, account.getAccountId());
                stmt.setDouble(2, account.getBalance());
                stmt.setString(3, account.getName());
                stmt.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public Object[] getAccount(int accountId) {
        Object[] accountData = null;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT balance, owner_name FROM accounts WHERE account_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, accountId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        double balance = rs.getDouble("balance");
                        String ownerName = rs.getString("owner_name");
                        accountData = new Object[]{accountId, balance, ownerName};
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return accountData;
    }

    public void displayAccount(int accountId) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, accountId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Account ID: " + rs.getInt("account_id"));
                        System.out.println("Balance: " + rs.getDouble("balance"));
                        System.out.println("Owner Name: " + rs.getString("owner_name"));
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void removeAccount(Account account) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM accounts WHERE account_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, account.getAccountId());
                stmt.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
