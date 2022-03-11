package com.concurrent.basic.ch02;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:20
 * @Description: com.concurrent.basic.ch02
 * @version: 1.0
 */
public class ImpRunnable2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("我是t1线程");
        });
        t1.start();
    }
}
