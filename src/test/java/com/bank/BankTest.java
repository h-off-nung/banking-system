package com.bank;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void testAddAccount() {
        // Test adding an account to the bank
        Bank bank = new Bank();
        Account account = new Account(1, 1000, "John Doe");
        bank.addAccount(account);
        assertNotNull(bank.getAccount(1));
    }

    @Test
    public void testRemoveAccount() {
        // Test removing an account from the bank
        Bank bank = new Bank();
        Account account = new Account(1, 1000, "Jane Smith");
        bank.addAccount(account);
        bank.removeAccount(account);
        assertNull(bank.getAccount(1));
    }

    @Test
    public void testBlockAccount() {
        // Test blocking an account in the bank
        Bank bank = new Bank();
        Account account = new Account(1, 1000, "Alice");
        bank.addAccount(account);
        bank.blockAccount(1);
        assertTrue(bank.isAccountBlocked(1));
    }

    @Test
    public void testUnblockAccount() {
        // Test unblocking a blocked account in the bank
        Bank bank = new Bank();
        Account account = new Account(1, 1000, "Bob");
        bank.addAccount(account);
        bank.blockAccount(1);
        bank.unblockAccount(1);
        assertFalse(bank.isAccountBlocked(1));
    }

    // Add more test methods as needed

}