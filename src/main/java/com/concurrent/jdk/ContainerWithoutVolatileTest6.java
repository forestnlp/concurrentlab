package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


/**
 * 使用LockSupport
 */
public class ContainerWithoutVolatileTest6 {
    volatile List<Integer> container = new ArrayList<>();

    void add(int a) {
        System.out.println(" add " + a + "");
        container.add(a);
    }

    int size() {
        return container.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        ContainerWithoutVolatileTest2 test = new ContainerWithoutVolatileTest2();

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.printf("size ==5 ");
            LockSupport.unpark(t1);
        });
        t2.start();


        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test.add(i);
                if (i == 4) {
                    LockSupport.unpark(t2);
                    LockSupport.park(t1);
                }
            }
        }, "t1");
        t1.start();

    }
}