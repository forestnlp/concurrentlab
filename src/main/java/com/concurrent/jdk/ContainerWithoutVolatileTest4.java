package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * 使用门闩
 */
public class ContainerWithoutVolatileTest4 {
    volatile List<Integer> container = new ArrayList<>();
    void add(int a){
        System.out.println(" add "+a+"");
        container.add(a);
    }

    int size(){
        return container.size();
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ContainerWithoutVolatileTest2 test = new ContainerWithoutVolatileTest2();

        Thread t2 = new Thread(()->{
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("catch! size==5");
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(()->{
                for(int i=0;i<10;i++) {
                    test.add(i);
                    latch.countDown();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"t1");
        t1.start();

    }
}