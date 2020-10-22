package com.concurrent.jdk;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    ReadWriteLock rwlock = new ReentrantReadWriteLock();
    Lock rlock = rwlock.readLock();
    Lock wlock = rwlock.writeLock();

    String val = "读写锁";

    void read() {
        try {
            rlock.lock();
            Thread.sleep(10);
            System.out.println("read finished");
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
            System.out.println("read finished");
            val += str;
            System.out.println(val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wlock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest t = new ReadWriteLockTest();
        for(int i=0;i<100;i++)
            new Thread(t::read,"读线程"+i).start();

        for(int i=0;i<20;i++)
            new Thread(()->{
                t.write(System.currentTimeMillis()+"写入");
            }).start();
    }
}
