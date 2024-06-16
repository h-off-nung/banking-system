package com.bank;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Database {
    private static String url;
    private static String user;
    private static String password;

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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
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

    public void saveTransaction(Transaction transaction) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO transactions (sender_id, recipient_id, amount, transaction_type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, transaction.getSenderId());
                stmt.setInt(2, transaction.getRecipientId());
                stmt.setDouble(3, transaction.getAmount());
                stmt.setString(4, transaction.getType());
                stmt.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Object[] getTransaction(int accountId) {
        Object[] transactionData = null;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM transactions WHERE sender_id = ? AND recipient_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, accountId);
                stmt.setInt(2, accountId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int senderId = rs.getInt("sender_id");
                        int recipientId = rs.getInt("recipient_id");
                        double amount = rs.getDouble("amount");
                        String type = rs.getString("transaction_type");
                        transactionData = new Object[]{senderId, recipientId, amount, type};
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return transactionData;
    }

    public void removeTransaction(Transaction transaction) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM transactions WHERE sender_id = ? AND recipient_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, transaction.getSenderId());
                stmt.setInt(2, transaction.getRecipientId());
                stmt.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
