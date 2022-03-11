package com.concurrent.basic.ch03;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 12:17
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class InterruptToStop3 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(()->{
            synchronized (o) {
                System.out.println(LocalDateTime.now()+",t1:我获得了锁，我将占有10秒钟");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        //保证t1先执行
        Thread.sleep(50);

        Thread t2 = new Thread(()->{
            System.out.println(LocalDateTime.now()+",t2 我将争抢锁");
           synchronized (o) {
               System.out.println(LocalDateTime.now()+",t2:我终于抢到锁了");
           }
        });
        t2.start();

        Thread.sleep(50);
        System.out.println("t2 状态："+ t2.getState());
        //此时无法打断。进入blocked状态。
        t2.interrupt();
    }
}
