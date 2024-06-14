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
}