package com.concurrent.juc.ch11;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {
    public static void main(String[] args) {
/*
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("request a thread,and build one");
                return new Thread(r);
            }
        };

        factory.newThread(()->{
            System.out.println("gogogo!");
        }).start();
*/

        ThreadFactory factory = Executors.defaultThreadFactory();
        factory.newThread(()->{
            System.out.println("come!!!");
        }).start();


    }
}
