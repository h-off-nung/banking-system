package com.bank;

public class AccountTester {
    public static void main(String[] args) {
        AccountTest accountTest = new AccountTest();
        accountTest.testAccount();
        accountTest.testDeposit();
        accountTest.testWithdraw();
        accountTest.testSetBlocked();
        accountTest.testToString();
    }
}
