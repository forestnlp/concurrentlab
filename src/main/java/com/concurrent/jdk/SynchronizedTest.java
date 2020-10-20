package com.concurrent.jdk;

import java.util.Date;

public class SynchronizedTest {

    static int i = 0;

    public static  void  count() {
        synchronized(new Object()){
            while (true) {
                i++;
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            SynchronizedTest.count();
        }).start();

        new Thread(()->{
            SynchronizedTest.count();
        }).start();
    }
}
