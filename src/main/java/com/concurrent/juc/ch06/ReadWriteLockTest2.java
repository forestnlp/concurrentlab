package com.concurrent.juc.ch06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest2 {
    Lock lock = new ReentrantLock();

    String val = "读写锁";
    CountDownLatch latch = new CountDownLatch(120);

    void read() {
        try {
            lock.lock();
            Thread.sleep(100);
            System.out.println("read finished");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void write(String str) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write finished");
            val = str;
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockTest2 t = new ReadWriteLockTest2();

        long start = System.currentTimeMillis();
        for(int i=0;i<100;i++)
            new Thread(t::read,"读线程"+i).start();

        for(int i=0;i<20;i++)
            new Thread(()->{
                t.write(System.currentTimeMillis()+"写入");
            }).start();

        t.latch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }
}
