package com.concurrent.juc.ch03;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch1 = new CountDownLatch(10);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+":处理任务1");
                latch1.countDown();
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        latch1.await();
        System.out.println("所有任务 都被处理完毕");
    }
}
