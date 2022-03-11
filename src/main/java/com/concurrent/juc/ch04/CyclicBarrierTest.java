package com.concurrent.juc.ch04;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人发车.");
            }
        });

        for(int i=0;i<100;i++) {
            Thread.sleep(new Random().nextInt(1000));
            new Thread(()->{
                try {
                    //做一些业务逻辑
                    System.out.println(Thread.currentThread().getName() +" 满足条件");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
