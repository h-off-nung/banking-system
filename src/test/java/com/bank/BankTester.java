package com.bank;

public class BankTester {

    public static void main(String[] args) {
        BankTest bankTest = new BankTest();
        bankTest.testAddAccount();
        bankTest.testRemoveAccount();
        bankTest.testBlockAccount();
        bankTest.testUnblockAccount();
    }
}
