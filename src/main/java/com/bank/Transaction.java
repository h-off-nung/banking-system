package com.bank;

public class Transaction {
    private Account sender;
    private Account recipient;
    private double amount;
    private String type;

    // For transactions within the bank
    public Transaction(Account sender, Account recipient, double amount, String type) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.type = type;
    }
    // For withdraws and transactions outside the bank
    public Transaction(Account sender, double amount, String type) {
        this.sender = sender;
        this.amount = amount;
        this.type = type;
    }
    // For deposits
    public Transaction(double amount, Account recipient) {
        this.recipient = recipient;
        this.amount = amount;
    }

    public String toString() {
        if (recipient != null) {
            return "Sender: " + sender.getName() + ", Recipient: " + recipient.getName() + ", Amount: " + amount + ", Type: " + type;
        }
        return "Sender: " + sender.getName() + ", Amount: " + amount + ", Type: " + type;
    }

    public Account getSender() {
        return sender;
    }
    public Account getRecipient() {
        return recipient;
    }
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return type;
    }
}
