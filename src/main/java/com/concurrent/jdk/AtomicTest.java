package com.concurrent.jdk;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicTest {
    static AtomicLong cnt = new AtomicLong(0l);

    static void m() {
        for (int i = 0; i < 10000; i++)
            cnt.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++)
            threads[i] = new Thread(() -> {
                AtomicTest.m();
            });

        long start = System.currentTimeMillis();
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }

        for(int i=0;i<threads.length;i++){
            threads[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println(end-start);
        System.out.println(AtomicTest.cnt.get());

    }
}
