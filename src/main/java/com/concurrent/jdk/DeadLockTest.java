package com.concurrent.jdk;

public class DeadLockTest {

    Object o1 = new Object();
    Object o2 = new Object();

    void  m1(){
        synchronized(o1) {
            System.out.println("m1");
            synchronized(o2) {
                System.out.println("m1-2");
            }
        }
    }

    void  m2(){
        synchronized(o2) {
            System.out.println("m2");
            synchronized(o1) {
                System.out.println("m2-1");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockTest test = new DeadLockTest();

        new Thread(()->{
            test.m1();

        }).start();


        new Thread(()->{

            test.m2();

        }).start();
    }

}
