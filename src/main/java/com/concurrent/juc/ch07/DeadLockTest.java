package com.concurrent.juc.ch07;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;

public class DeadLockTest {

    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 semaphore requiring");
                semaphore1.acquire();
                Thread.sleep(1000);
                semaphore2.acquire();
                semaphore2.release();
                semaphore1.release();
            } catch (InterruptedException e) {

            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 semaphore requiring");
                semaphore2.acquire();
                Thread.sleep(1000);
                semaphore1.acquire();
                semaphore1.release();
                semaphore2.release();
            } catch (InterruptedException e) {

            }
        });
        t1.start();
        t2.start();
    }
}
