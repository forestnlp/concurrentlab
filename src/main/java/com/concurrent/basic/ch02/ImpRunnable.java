package com.concurrent.basic.ch02;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:20
 * @Description: com.concurrent.basic.ch02
 * @version: 1.0
 */
public class ImpRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("我是t1线程");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ImpRunnable());
        t1.start();
    }
}
