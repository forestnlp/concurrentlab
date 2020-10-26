package com.concurrent.jdk;


public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " :" + threadLocal.get());

        for (int i = 0; i < 100; i++) {
            threadLocal.set("a" + i);
            System.out.println(Thread.currentThread().getName() + " :" + threadLocal.get());
        }

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                threadLocal.set("b" + i);
            }
        }).start();
    }
}