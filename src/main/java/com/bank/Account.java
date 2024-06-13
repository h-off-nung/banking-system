package com.bank;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger accountIdCounter = new AtomicInteger(1);
    private final int accountId;
    private double balance;
    private final String name;
    private boolean blocked;

    public Account( double balance, String name) {
        this.accountId = generateAccountId();
        this.balance = balance;
        this.name = name;
        this.blocked = false;
    }

    public String toString() {
        return "Account Number: " + accountId + ", Balance: " + balance + ", Name: " + name + ", Blocked: " + blocked;
    }

    private int generateAccountId() {
        return accountIdCounter.getAndIncrement();
    }

    public int getAccountId() {
        return accountId;
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