package com.concurrent.basic.ch01;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/7 - 03 - 07 - 16:18
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class ThreadStateMonitor1 {
    public static void main(String[] args) throws InterruptedException {
        /*
        观察 NEW RUNNABLE TERMINATED 状态
        * */
        Thread t1 = new Thread(()->{
            for(int i=0;i<3;i++) {
                System.out.print(Thread.currentThread().getState());
                System.out.println("do something " + i);
            }
        });
        System.out.println(t1.getState());
        t1.start();
        //join 方法在此处表示，main线程等待t1完成线程方法。
        t1.join();
        System.out.println(t1.getState());
    }
}
