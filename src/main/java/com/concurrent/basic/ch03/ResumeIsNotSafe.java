package com.concurrent.basic.ch03;

import java.time.LocalDateTime;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/7 - 03 - 07 - 17:50
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class ResumeIsNotSafe {

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        Thread t1 = new Thread(()->{
            for(int i=0;i<5;i++) {
                synchronized (o) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("t1 :"+ LocalDateTime.now());
                }
            }
        });
        t1.start();

        Thread.sleep(100);
        t1.suspend();
        Thread.sleep(10000);
        //如果resume失效了。则无法执行t2了。
        System.out.println("此时，t1进入暂停状态，但是它仍然持有锁");
        //t1.resume();

        Thread t2 = new Thread(()->{
            for(int i=0;i<5;i++) {
                synchronized (o) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("t2 :"+ LocalDateTime.now());
                }
            }
        });
        t2.start();
    }
}
