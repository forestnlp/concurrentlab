package com.concurrent.jdk;

public class ReentranceTest {


    public synchronized void m1() {

    }
    public synchronized void m2() {

    }

    public static void main(String[] args) {
        ReentranceTest lock = new ReentranceTest();
        new Thread(()->{
            lock.m1();
            lock.m2();
        }).start();
    }
}
