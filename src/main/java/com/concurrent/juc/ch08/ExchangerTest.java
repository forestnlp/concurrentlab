package com.concurrent.juc.ch08;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    Exchanger<String> exchanger = new Exchanger<>();

    void m(String s){
        try {
            String s2 = exchanger.exchange(s);
            System.out.println(Thread.currentThread().getName()+" s="+s+"，交换后，s2="+s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExchangerTest test = new ExchangerTest();
        while (true) {
            new Thread(()->{test.m("123");}).start();
            new Thread(()->{test.m("abc");}).start();
            new Thread(()->{test.m("456");}).start();
            new Thread(()->{test.m("edf");}).start();
            Thread.sleep(1000);
        }
    }
}
