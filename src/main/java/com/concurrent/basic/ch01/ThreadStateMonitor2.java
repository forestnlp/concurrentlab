package com.concurrent.basic.ch01;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/7 - 03 - 07 - 16:18
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class ThreadStateMonitor2 {
    public static void main(String[] args) throws InterruptedException {
        //o是一个锁对象
        Object o = new Object();
        //让t1线程获得o的锁后，就等待被唤醒
        Thread t1 = new Thread(()->{
            synchronized (o) {
                System.out.println("我获得了锁");
                try {
                    //o.wait(1000);
                    o.wait();
                    System.out.println("我wait好了。我执行到这里的时候，表示又获得了锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        //MAIN线程让t1先执行一会
        Thread.sleep(100);
        //这个时候t1已经wait了
        System.out.println(t1.getState());
        synchronized (o){
            o.notify();
            System.out.println(t1.getState());
        }
        Thread.sleep(100);
        //t1
        t1.join();
        System.out.println(t1.getState());
    }
}
