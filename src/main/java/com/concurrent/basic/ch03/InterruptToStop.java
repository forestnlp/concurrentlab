package com.concurrent.basic.ch03;

import java.time.LocalDateTime;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 11:57
 * @Description: com.concurrent.basic
 * @version: 1.0
 */
public class InterruptToStop {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(()->{
            synchronized(o) {
                System.out.println(LocalDateTime.now()+" t1:等等我先喝口水上个厕所");
                try {
                    Thread.sleep(10000);
                    //对于正在wait的线程也可以中断哦。
                    //o.wait();
                } catch (InterruptedException e) {
                    System.out.println(LocalDateTime.now()+" t1:摸鱼的我，被打断了，赶紧善后。");
                }
                System.out.println(LocalDateTime.now()+" t1:事情忙完了。");
            }
        });

        Thread t2 = new Thread(()->{
            synchronized(o) {
                System.out.println(LocalDateTime.now()+" t2:终于轮到我上场了");
            }
        });

        t1.start();
        //确保t1先启动，拿到锁对象o。
        Thread.sleep(50);
        t2.start();
        t1.interrupt();
    }
}
