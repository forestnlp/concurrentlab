package com.concurrent.juc.ch07;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreTest2 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10, true);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + " " + new Date() + " go ,排队的有" + semaphore.getQueueLength());
                    Thread.sleep(1000);
                    semaphore.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
    }
}
