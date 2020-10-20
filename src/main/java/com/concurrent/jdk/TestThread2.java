package com.concurrent.jdk;

import java.util.Date;

public class TestThread2 {
    static void testSleep() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("wake " + new Date().toLocaleString());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testJoin() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("t1 thread " + new Date().toLocaleString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                for (int i = 0; i < 10; i++) {
                    System.out.println("t2 thread " + new Date().toLocaleString());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) Thread.yield();
                System.out.println("A" + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) Thread.yield();
                System.out.println("B" + i);
            }
        }).start();
    }

    public static void main(String[] args) {
        //testSleep();
        //testJoin();
        testYield();
    }
}
