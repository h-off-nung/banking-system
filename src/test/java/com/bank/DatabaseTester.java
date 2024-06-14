package com.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTester {

    public static void main(String[] args) {
        DatabaseTest tester = new DatabaseTest();
        tester.testSaveAccount();
        tester.testRemoveAccount();
    }
}