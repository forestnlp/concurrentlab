package com.concurrent.jdk;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class VolatileTest {
    volatile  boolean flag = true;
    //boolean flag = true;

    void m(){
        System.out.println("start");
        while (flag) {
            System.out.println(LocalDateTime.now());
           // int i;
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        VolatileTest t = new VolatileTest();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.flag=false;
    }
}
