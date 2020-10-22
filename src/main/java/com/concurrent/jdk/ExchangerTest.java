package com.concurrent.jdk;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    Exchanger<String> exchanger = new Exchanger<>();

    void m(String s){
        try {
            System.out.println("step1 "+Thread.currentThread().getName()+" s="+s);
            s = exchanger.exchange(s);
            System.out.println("step2 "+Thread.currentThread().getName()+" s="+s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {
        ExchangerTest test = new ExchangerTest();
        new Thread(()->{test.m("123");}).start();
        new Thread(()->{test.m("789");}).start();
        new Thread(()->{test.m("456");}).start();
    }
}
