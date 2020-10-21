package com.concurrent.jdk;

public class ReentranceLockTest {
    int i=0;

    synchronized void m1(){
        i++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2(){
        System.out.println(i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            new ReentranceLockTest().m1();
        }).start();
    }
}
