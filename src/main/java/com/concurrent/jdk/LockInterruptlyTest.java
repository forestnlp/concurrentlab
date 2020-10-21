package com.concurrent.jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptlyTest {
    Lock lock = new ReentrantLock();
    void m1(){
        try {
            lock.lock();
            for(int i=0;i<1000;i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("m1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lockInterruptibly();
            System.out.println("m2 executed");
        }
        catch (InterruptedException e){
            System.out.println("interrupted");
        }
        finally {
            System.out.println("m2 fin");
        }
    }

    public static void main(String[] args) {

        LockInterruptlyTest test = new LockInterruptlyTest();

        Thread t1 = new Thread(test::m1,"t1");
        t1.start();

        long start = System.currentTimeMillis();
        Thread t2 = new  Thread(test::m2,"t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
