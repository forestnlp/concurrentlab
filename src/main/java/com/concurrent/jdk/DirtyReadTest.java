package com.concurrent.jdk;

import java.sql.SQLOutput;

public class DirtyReadTest {
    static class Account {
        private String name;
        private double balance;

        public synchronized void setAccount(String name, double balance) {
            this.name = name;
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.balance = balance;
        }

        public double getBalance() {
            synchronized (new Object()) {
                return balance;
            }
        }

        @Override
        public String toString() {
            return "Account{" +
                    "name='" + name + '\'' +
                    ", balance=" + balance +
                    '}';
        }
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(() -> {
            account.setAccount("jie", 2000000);
            System.out.println(account);
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance());
    }

}
