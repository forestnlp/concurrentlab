package com.concurrent.jdk;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranceLockTest2 {
    int i = 0;
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            while (true)
            {
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m2();
            }
        } finally {
            lock.lock();
        }
    }

    void m2() {
        try {
            lock.lock();

            System.out.println(i);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentranceLockTest2 test2 = new ReentranceLockTest2();
        new Thread(test2::m1,"t1").start();
    }
}
