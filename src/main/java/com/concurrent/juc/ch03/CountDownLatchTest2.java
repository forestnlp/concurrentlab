package com.concurrent.juc.ch03;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest2 implements Runnable{

     CountDownLatch latch = new CountDownLatch(10);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is working ");
        latch.countDown();
    }

    public void done(){
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        CountDownLatchTest2 test = new CountDownLatchTest2();
        for(int i=0;i<threads.length;i++) {
            new Thread(test).start();
        }
        test.done();
    }
}
