package com.concurrent.juc.ch09;

import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i=0;i<100;i++) {
                if(i%3==0) LockSupport.park();
                System.out.println(" i="+i+" ,date="+new Date());
            }
        });
        t.start();

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(t);
        }
    }
}