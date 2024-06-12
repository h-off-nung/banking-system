package com.bank;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testAccount() {
        Account account = new Account(1, 1000, "John Wick");
        assertEquals(1, account.getAccountNumber());
        assertEquals(1000, account.getBalance(), 0.01);
        assertEquals("John Wick", account.getName());
        assertFalse(account.isBlocked());
    }

    @Test
    public void testDeposit() {
        Account account = new Account(1, 1000, "John Wick");
        account.deposit(500);
        assertEquals(1500, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdraw() {
        Account account = new Account(1, 1000, "John Wick");
        account.withdraw(500);
        assertEquals(500, account.getBalance(), 0.01);
    }

    @Test
    public void testSetBlocked() {
        Account account = new Account(1, 1000, "John Wick");
        account.setBlocked(true);
        assertTrue(account.isBlocked());
    }

    @Test
    public void testToString() {
        Account account = new Account(1, 1000, "John Wick");
        String expected = "Account Number: 1, Balance: 1000.0, Name: John Wick, Blocked: false";
        assertEquals(expected, account.toString());
    }
}