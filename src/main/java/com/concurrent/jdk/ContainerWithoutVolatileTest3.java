package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * wait 和 notify 配合使用
 */
public class ContainerWithoutVolatileTest3 {
    volatile List<Integer> container = new ArrayList<>();
    void add(int a){
        System.out.println(" add "+a+"");
        container.add(a);
    }

    int size(){
        return container.size();
    }

    public static void main(String[] args) {
        Object o = new Object();
        ContainerWithoutVolatileTest2 test = new ContainerWithoutVolatileTest2();

        Thread t2 = new Thread(()->{
            synchronized (o){
                if(test.size()!=5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("catch！ size==5");
                o.notify();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(()->{
            synchronized (o){
                for(int i=0;i<10;i++) {
                    test.add(i);
                    if(i==4) {
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1");
        t1.start();

    }
}