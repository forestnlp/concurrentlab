package com.concurrent.jdk;

import java.util.concurrent.TimeUnit;

public class VolatileTest2 {

    class O{
        boolean b = true;
    }

    //volatile  boolean flag = true;
    volatile O flag = new O();


    void m(){
        System.out.println("start");
        while (flag.b) {

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        VolatileTest2 t = new VolatileTest2();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.flag.b=false;
    }
}
