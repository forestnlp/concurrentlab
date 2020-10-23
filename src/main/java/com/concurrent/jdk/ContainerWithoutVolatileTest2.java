package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 这种方式还是不行，notify的时候没有立刻释放锁，而是等待任务完成，因此不可行
 */
public class ContainerWithoutVolatileTest2 {
     volatile List<Integer> container = new ArrayList<>();
     void add(int a){
        System.out.printf("add "+a);
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
                if(test.size()==5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
                    if(i==5) o.notify();
                }
            }
        },"t1");
        t1.start();

    }
}
