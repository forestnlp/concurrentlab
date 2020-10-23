package com.concurrent.jdk;

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest<T> {
    ReentrantLock lock = new ReentrantLock();
    Condition producerCond = lock.newCondition();
    Condition consumerCond = lock.newCondition();

    final private LinkedList<T> container = new LinkedList<T>();
    final private int MAXSIZE = 20;

    public void put(T t) {
        lock.lock();
        try {
            while (container.size() == MAXSIZE) {
                System.out.println("wait for consumer");
                producerCond.await();
            }
            container.add(t);
            System.out.println(Thread.currentThread().getName() + " put " + t + " container = " + container);
            consumerCond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        lock.lock();
        try {
            while (container.size() == 0) {
                System.out.println("wait for producer");
                consumerCond.await();
            }
            t = container.removeFirst();
            System.out.println(Thread.currentThread().getName() + " get " + t + " container = " + container);
            producerCond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        ConditionTest<Integer> container = new ConditionTest();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {

            new Thread(() -> {
                container.put(random.nextInt(100));
            }, "producer " + i).start();

            new Thread(() -> {
                int o = container.get();
            }, "consumer " + i).start();

            new Thread(() -> {
                container.put(random.nextInt(100));
            }, "producer " + i).start();

            new Thread(() -> {
                int o = container.get();
            }, "consumer " + i).start();
        }

    }
}
