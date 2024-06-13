package com.bank;

import java.util.ArrayList;

public class Bank {
    private final ArrayList<Account> accounts;
    private final ArrayList<Transaction> transactions;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

// OPERATIONS WITH ACCOUNTS

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }
    public Account getAccount(int accountNumber) {
        for (Account account : this.accounts) {
            if (account.getAccountId() == accountNumber) {
                return account;
            }
        }
        return null;
    }
    public void blockAccount(int accountNumber) {
        Account account = this.getAccount(accountNumber);
        if (account != null) {
            account.setBlocked(true);
        }
    }
    public void unblockAccount(int accountNumber) {
        Account account = this.getAccount(accountNumber);
        if (account != null) {
            account.setBlocked(false);
        }
    }
    public boolean isAccountBlocked(int accountNumber) {
        Account account = this.getAccount(accountNumber);
        if (account != null) {
            return account.isBlocked();
        }
        return false;
    }

// EXECUTION OF TRANSACTIONS

    public void transaction(Account sender, Account recipient, double amount) {
        if (!sender.isBlocked() && !recipient.isBlocked()) {
            if (amount > 10000) {
                sender.setBlocked(true);
                System.out.println("Sender account blocked due to large transaction attempt.");
                return;
            }
            sender.withdraw(amount);
            recipient.deposit(amount);
            Transaction transaction = new Transaction(sender, recipient, amount, "Transfer");
            this.transactions.add(transaction);
        }
    }
    public void transaction(Account sender, double amount, String type) {
        if (!sender.isBlocked()) {
            if (amount > 10000) {
                sender.setBlocked(true);
                System.out.println("Sender account blocked due to large transaction attempt.");
                return;
            }
            sender.withdraw(amount);
            Transaction transaction = new Transaction(sender, amount, type);
            this.transactions.add(transaction);
        }
    }

// OPERATIONS WITH THE DATABASE

    public ArrayList<Transaction> getTransactions(Account account) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            if (transaction.getSender().equals(account) || transaction.getRecipient().equals(account)) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }
    public void displayAccountTransactions(Account account) {
        ArrayList<Transaction> accountTransactions = this.getTransactions(account);
        for (Transaction transaction : accountTransactions) {
            System.out.println(transaction);
        }
    }
    public void displayAllTransactions() {
        for (Transaction transaction : this.transactions) {
            System.out.println(transaction);
        }
    }

    public ArrayList<Transaction> getAllTransactions() {
        return this.transactions;
    }
}
