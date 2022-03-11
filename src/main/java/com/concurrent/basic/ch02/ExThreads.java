package com.concurrent.basic.ch02;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:16
 * @Description: com.concurrent.basic.ch02
 * @version: 1.0
 */
public class ExThreads extends Thread {
    @Override
    public void run() {
        System.out.println("我是t1线程");
    }

    public static void main(String[] args) {
        Thread t1 = new ExThreads();
        t1.start();
    }
}
