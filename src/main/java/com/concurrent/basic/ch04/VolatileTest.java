package com.concurrent.basic.ch04;

import java.time.LocalDateTime;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:56
 * @Description: com.concurrent.basic.ch04
 * @version: 1.0
 */
public class VolatileTest {
    private  static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) {
            }
            System.out.println("over");
        }).start();

        Thread.sleep(5);

        flag=false;
    }
}
