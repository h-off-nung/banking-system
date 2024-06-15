package com.bank;

public class Transaction {
    private final int senderId;
    private final int recipientId;
    private final double amount;
    private final String type;

    // For transactions within the bank
    public Transaction(int sender, int recipient, double amount, String type) {
        this.senderId = sender;
        this.recipientId = recipient;
        this.amount = amount;
        this.type = type;
    }
    // For withdraws and transactions outside the bank
    public Transaction(int sender, double amount, String type) {
        this.senderId = sender;
        this.recipientId = 0;
        this.amount = amount;
        this.type = type;
    }

//    public String toString() {
//        if (recipient != 0) {
//            return "Sender: " + sender.getName() + ", Recipient: " + recipient.getName() + ", Amount: " + amount + ", Type: " + type;
//        }
//        return "Sender: " + sender.getName() + ", Amount: " + amount + ", Type: " + type;
//    }

    public double getAmount() {
        return amount;
    }
    public String getType() {
        return type;
    }

    public int getSenderId() {
        return senderId;
    }
    public int getRecipientId() {
        return recipientId;
    }
}
