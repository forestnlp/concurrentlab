package com.concurrent.juc.ch02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {
    Lock lock = new ReentrantLock();
    void A(){
        System.out.println("A begin");
        try {
            lock.lock();
            for(int i=0;i<10;i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("m1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            lock.unlock();
        }
    }

    void B(){
        System.out.println("B begin");
        try {
            lock.tryLock(2,TimeUnit.SECONDS);
            System.out.println("m2 get lock");
        }
        catch (InterruptedException e) {
            System.out.println("B ，太久了，我不等了");
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TryLockTest test = new TryLockTest();

        new Thread(test::A,"t1").start();

        Thread.sleep(1000);

        new Thread(test::B,"t2").start();

    }
}
