package com.concurrent.juc.ch01;

import java.time.LocalDateTime;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 16:35
 * @Description: com.concurrent.basic.ch05
 * @version: 1.0
 */
public class ConditionRaceExp {

    private static volatile int cnt = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[1000];

        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                synchronized(ConditionRaceExp.class) {
                    for (int k = 0; k < 10000; k++) {
                            cnt++;
                    }
                }
            });
        }

        long start = System.currentTimeMillis();
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }

        for(int i=0;i<threads.length;i++){
            threads[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println(end-start);
        System.out.println(cnt);
    }
}
