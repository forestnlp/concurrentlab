package com.concurrent.juc.ch02;

public class ReentranceTest {

    public synchronized void m1() {
        System.out.println("我进入到m1方法了");
    }
    public synchronized void m2() {
        System.out.println("我进入到m2方法了");
    }

    public static void main(String[] args) {
        ReentranceTest o = new ReentranceTest();
        new Thread(()->{
            o.m1();
            o.m2();
        }).start();
    }
}
