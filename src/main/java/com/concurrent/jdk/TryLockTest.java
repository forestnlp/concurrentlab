package com.concurrent.jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {
    Lock lock = new ReentrantLock();
    void m1(){
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

    void m2(){
        System.out.println("m2 begin");
        try {
            lock.tryLock(15,TimeUnit.SECONDS);
            System.out.println("m2 get lock");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        TryLockTest test = new TryLockTest();

        new Thread(test::m1,"t1").start();

        new Thread(test::m2,"t2").start();

    }
}
