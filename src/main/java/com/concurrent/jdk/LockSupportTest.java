package com.concurrent.jdk;

import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i=0;i<100;i++) {
                if(i%5==0) LockSupport.park();
                System.out.println("threat"+Thread.currentThread().getName()+" runs,i="+i+" ,date="+new Date());
            }
        });
        t.start();


        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(t);
        }

    }
}