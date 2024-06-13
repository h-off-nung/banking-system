package com.bank;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Account account1 = new Account( 1000, "John Wick");
        Account account2 = new Account( 65000, "Tony Stark");
        Account account3 = new Account( 4250, "Megan Fox");
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);

        bank.transaction(account3, account1, 250);
        bank.transaction(account2, 3500, "Buying a super coffee");
        bank.transaction(account1, 50, "Withdraw");

        bank.displayAllTransactions();

        bank.transaction(account2, account3, 15000);
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
    }
}