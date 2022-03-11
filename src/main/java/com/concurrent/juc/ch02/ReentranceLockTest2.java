package com.concurrent.juc.ch02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranceLockTest2 {
    Lock lock = new ReentrantLock(true);

    void A() {
        try {
//            lock.lock();
            System.out.println("A被调用了");
            B();
        } finally {
            lock.lock();
        }
    }

    void B() {
        try {
            lock.lock();
            System.out.println("B被调用了");
        } finally {
            lock.lock();
        }
    }

    public static void main(String[] args) {
        ReentranceLockTest2 test2 = new ReentranceLockTest2();
        new Thread(test2::A,"t1").start();
    }
}
