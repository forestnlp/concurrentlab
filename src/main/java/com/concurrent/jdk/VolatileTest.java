package com.concurrent.jdk;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    //volatile  boolean flag = true;
     boolean flag = true;

    void m(){
        System.out.println("start");
        while (flag) {
        //    System.out.println("running");
            int i;
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        VolatileTest t = new VolatileTest();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.flag=false;
    }
}
