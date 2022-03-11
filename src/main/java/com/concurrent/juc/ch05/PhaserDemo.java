package com.concurrent.juc.ch05;

import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserDemo {

    static class KillerGamePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("第一次迭代，存活人数:" + registeredParties);
                    return false;
                case 1:
                    System.out.println("第二次迭代，存活人数:" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static Phaser phaser = new KillerGamePhaser();

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        Random rand = new Random();
        phaser.bulkRegister(10);
        for (Thread t : threads) {
            t = new Thread(() -> {
                phaser.arriveAndAwaitAdvance();

                if(rand.nextInt(10)>5){
                    System.out.println(Thread.currentThread().getName()+ "我成功了");
                    phaser.arriveAndAwaitAdvance();
                }
                else{
                    System.out.println(Thread.currentThread().getName()+ "黯然离场");
                    phaser.arriveAndDeregister();
                }
            });
            t.start();
        }
    }
}
