package com.concurrent.juc.ch06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    ReadWriteLock rwlock = new ReentrantReadWriteLock();
    Lock rlock = rwlock.readLock();
    Lock wlock = rwlock.writeLock();

    String val = "读写锁";
    CountDownLatch latch = new CountDownLatch(120);

    void read() {
        try {
            rlock.lock();
            Thread.sleep(100);
            System.out.println("read finished");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rlock.unlock();
        }
    }

    void write(String str) {
        try {
            wlock.lock();
            Thread.sleep(1000);
            System.out.println("write finished");
            val = str;
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wlock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockTest t = new ReadWriteLockTest();

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
