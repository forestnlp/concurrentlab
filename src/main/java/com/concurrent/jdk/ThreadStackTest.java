package com.concurrent.jdk;

public class ThreadStackTest {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("a new Thread ");
            new Thread(()->{
                System.out.println("another thread start");
                Thread t = new Thread(()->{
                    System.out.println("daemon thread ");
                });
                t.setDaemon(true);
                t.start();
            }).start();
        }).start();
    }
}
