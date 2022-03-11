package com.concurrent.basic.ch05;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 16:35
 * @Description: com.concurrent.basic.ch05
 * @version: 1.0
 */
public class ConditionRaceExp {

    private static volatile int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[1000];

        for(int i=0;i<1000;i++){
            threads[i] = new Thread(()->{
                for(int k=0;k<10000;k++)
                    counter++;
            });
        }

        for(int i=0;i<1000;i++){
            threads[i].start();
        }

        for(int i=0;i<1000;i++){
            threads[i].join();
        }
        System.out.println(counter);
    }
}
