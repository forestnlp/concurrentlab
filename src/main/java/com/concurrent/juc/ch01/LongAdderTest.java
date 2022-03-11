package com.concurrent.juc.ch01;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    static LongAdder cnt = new LongAdder();

    static void m() {
        for (int i = 0; i < 10000; i++)
            cnt.increment();
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
