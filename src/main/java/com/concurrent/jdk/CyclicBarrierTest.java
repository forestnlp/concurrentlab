package com.concurrent.jdk;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
        @Override
        public void run() {
            System.out.println("满人发车..可以用于限流");
        }
    });

    public static void main(String[] args) {

        for(int i=0;i<100;i++) {
            new Thread(()->{
                try {
                    System.out.println("reach barrier");
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
