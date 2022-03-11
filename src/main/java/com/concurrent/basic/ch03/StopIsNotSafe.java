package com.concurrent.basic.ch03;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/7 - 03 - 07 - 17:37
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class StopIsNotSafe {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            while (true) {
                System.out.println("我在换衣服");
                System.out.println("我在脱裤子");
                System.out.println("我在穿裤子");
            }
        });

        t1.start();
        Thread.sleep(10);
        t1.stop();

        Object o = new Object();
        Thread t2 = new Thread(()->{
            synchronized (o) {
                while (true) {
                    System.out.println("t2 我在换衣服");
                    System.out.println("t2 我在脱裤子");
                    System.out.println("t2 我在穿裤子");
                }
            }
        });

        t2.start();
        Thread.sleep(10);
        t2.stop();
    }
}
