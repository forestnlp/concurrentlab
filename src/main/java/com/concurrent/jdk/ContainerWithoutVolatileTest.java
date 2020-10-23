package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContainerWithoutVolatileTest {

    /**
     * 感觉总是实现不了，用volatile、synchronize、或者同步队列都不行呢。
     */
    volatile List<Integer> container = Collections.synchronizedList(new ArrayList<Integer>());

    synchronized void add(int a){
        System.out.printf("add "+a);
        container.add(a);
    }

    int size(){
        return container.size();
    }

    public static void main(String[] args) {
        ContainerWithoutVolatileTest t = new ContainerWithoutVolatileTest();
        new Thread(()->{
            for(int i=0;i<10;i++)
                t.add(i);
        }).start();

        new Thread(()->{
            while (true){
                if(t.size()==5)
                    System.out.printf("size==5");
            }
        }).start();
    }
}
