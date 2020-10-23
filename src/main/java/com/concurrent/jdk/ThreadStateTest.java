package com.concurrent.jdk;

public class ThreadStateTest {
    public static void main(String[] args) {
       /* Thread t1 = new Thread(()->{
            System.out.println("thread runs");
        });
        //new
        System.out.println(t1.getName()+" ："+ t1.getState());
        //runnable
        t1.start();
        System.out.println(t1.getName()+" ："+ t1.getState());
*/

        Object o = new Object();
        Thread t2 = new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread runs");
            }
        },"t2");
        t2.start();
/*

        while (!t2.getState().toString().equals("TERMINATED")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t2.getName() + " ：" + t2.getState());
        }
*/

        Thread t3 = new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 5; i++) {
                    try {
                      //  Thread.sleep(1000);
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread runs");
            }
        },"t3");
        t3.start();

        while (!t3.getState().toString().equals("TERMINATED")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t3.getName() + " ：" + t3.getState());
        }
    }
}
