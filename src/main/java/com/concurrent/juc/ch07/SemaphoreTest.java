package com.concurrent.juc.ch07;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 semaphore requiring");
                semaphore.acquire();
                System.out.println("t1 semaphore require ok ，时间为" + LocalTime.now());
                Thread.sleep(10000);
                semaphore.release();
            } catch (InterruptedException e) {

            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 semaphore requiring");
                semaphore.acquire();
                System.out.println("t2 semaphore require ok，终于获取资源：" + LocalTime.now());
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {

            }
        });
        t1.start();
        t2.start();
    }
}
