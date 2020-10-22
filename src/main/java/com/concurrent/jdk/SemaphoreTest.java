package com.concurrent.jdk;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(()->{
            try {
                System.out.println("t1 semaphore requiring");
                semaphore.acquire();
                System.out.println("t1 semaphore require ok");
                Thread.sleep(100000);
                semaphore.release();
            }
            catch (InterruptedException e) {

            }
            finally {
            }

        });

        Thread t2 = new Thread(()->{
            try {
                System.out.println("t2 semaphore requiring");
                semaphore.acquire();
                System.out.println("t2 semaphore require ok");
                Thread.sleep(1000);
                semaphore.release();
            }
            catch (InterruptedException e) {

            }
            finally {
            }

        });
        t1.start();
        t2.start();
    }

}
