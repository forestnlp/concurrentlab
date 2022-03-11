package com.concurrent.juc.ch02;

public class ReentranceLockSynTest {

    synchronized void A(){
        System.out.println("A被调用了");
        B();
    }

    synchronized void B(){
        System.out.println("B被调用了");
    }

    public static void main(String[] args) {
        new Thread(()->{
            new ReentranceLockSynTest().A();
        }).start();
    }
}
