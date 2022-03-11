package com.concurrent.juc.ch03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/9 - 03 - 09 - 13:31
 * @Description: com.concurrent.juc.ch03
 * @version: 1.0
 */
public class CountDownLatchTest0 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch1 = new CountDownLatch(10);
        CountDownLatch latch2 = new CountDownLatch(10);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("处理任务1");
                latch1.countDown();
                //后续任务耗时比较久
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("处理任务2");
                latch2.countDown();
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        latch1.await();
        System.out.println("所有任务1 都被处理完毕");
        latch2.await();
        System.out.println("所有任务 都被处理完毕");
    }
}
