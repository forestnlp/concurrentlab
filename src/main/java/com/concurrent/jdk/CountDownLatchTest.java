package com.concurrent.jdk;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        int numofworkers = 10;
        for(int i=0;i<numofworkers;i++) {
            final  int k = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is working");
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("done");
    }
}
