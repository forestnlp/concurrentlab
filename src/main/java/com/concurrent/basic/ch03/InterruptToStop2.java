package com.concurrent.basic.ch03;

import java.time.LocalDateTime;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 12:17
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class InterruptToStop2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (!Thread.interrupted()) {
                System.out.println("t1 处理事情 "+ LocalDateTime.now());
            }
            System.out.println("我被打断了，做后面的事情");
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
