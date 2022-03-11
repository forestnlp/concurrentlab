package com.concurrent.basic.ch03;

import java.time.LocalDateTime;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 11:42
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class UsingFlagToStop {

    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        //线程t1每秒打印一次
        Thread t1 = new Thread(()->{
            while (flag) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1:"+ LocalDateTime.now());
            }
        });

        t1.start();
        Thread.sleep(4000);
        flag=false;
    }
}
