package com.concurrent.basic.ch03;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 12:33
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class InterruptToStop4 {
    public static void main(String[] args)  {
        ReentrantLock o = new ReentrantLock();
        Thread t1 = new Thread(()->{
            try {
                o.lock();
                System.out.println(LocalDateTime.now()+",t1:我获得了锁，我将占有10秒钟");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
            } finally {
                o.unlock();
            }
        });
        t1.start();

        //保证t1先执行
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        Thread t2 = new Thread(()->{
            System.out.println(LocalDateTime.now()+",t2 我将争抢锁");
            try {
                o.lockInterruptibly();
                System.out.println(LocalDateTime.now()+",t2 我终于拿到锁啦！！！");

            } catch (InterruptedException e) {
                System.out.println(LocalDateTime.now()+",t2 我被中断了！");
            }finally {
                if(o.isHeldByCurrentThread())
                o.unlock();
            }
        });
        t2.start();
        //保证t1先执行
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        System.out.println("t2 状态："+ t2.getState());
        //此时无法打断。进入blocked状态。
        t2.interrupt();
    }
}
