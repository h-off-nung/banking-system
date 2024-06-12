package com.bank;

public class Account {
    private int accountNumber;
    private double balance;
    private String name;
    private boolean blocked;

    public Account(int accountNumber, double balance, String name) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.blocked = false;
    }

    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: " + balance + ", Name: " + name + ", Blocked: " + blocked;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }

    public void deposit(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds");
        }
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean isBlocked() {
        return blocked;
    }
}