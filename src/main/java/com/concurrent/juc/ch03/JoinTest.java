package com.concurrent.juc.ch03;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/9 - 03 - 09 - 14:27
 * @Description: com.concurrent.juc.ch03
 * @version: 1.0
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":处理任务1");
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        //全部集结后再出发
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("所有任务 都被处理完毕");
    }
}
