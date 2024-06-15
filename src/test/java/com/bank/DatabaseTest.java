package com.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void testSaveAccount() {
        Database database = new Database();
        Account account = new Account(12345, "John Doe");
        database.saveAccount(account);
        // Assert that the account was saved successfully
        Object[] retrievedAccount = database.getAccount(account.getAccountId());
        assertEquals(account.getAccountId(), retrievedAccount[0]);
        assertEquals(account.getBalance(), retrievedAccount[1]);
        assertEquals(account.getName(), retrievedAccount[2]);
        // remove created row from database
        database.removeAccount(account);
    }

    @Test
    public void testRemoveAccount() {
        Database database = new Database();
        Account account = new Account(12345, "John Doe");
        database.saveAccount(account);
        database.removeAccount(account);
        // Assert that the account was removed successfully
        Object[] retrievedAccount = database.getAccount(account.getAccountId());
        assertNull(retrievedAccount);
        // remove created row from database
        database.removeAccount(account);
    }
    @Test
    public void testSaveTransaction() {
        Database database = new Database();
        Account sender = new Account(12345, "John Doe");
        Account recipient = new Account(23456, "Jane Doe");
        database.saveAccount(sender);
        database.saveAccount(recipient);
        Transaction transaction = new Transaction(sender.getAccountId(), sender.getAccountId(), 100.0, "deposit");
        System.out.println(transaction.getSenderId());
        System.out.println(transaction.getRecipientId());
        database.saveTransaction(transaction);
        // Assert that the transaction was saved successfully
        Object[] retrievedTransaction = database.getTransaction(transaction.getSenderId());
        assertEquals(transaction.getSenderId(), retrievedTransaction[0]);
        assertEquals(transaction.getRecipientId(), retrievedTransaction[1]);
        assertEquals(transaction.getAmount(), retrievedTransaction[2]);
        assertEquals(transaction.getType(), retrievedTransaction[3]);
        // remove created rows from database
        database.removeTransaction(transaction);
        database.removeAccount(sender);
        database.removeAccount(recipient);
    }
}