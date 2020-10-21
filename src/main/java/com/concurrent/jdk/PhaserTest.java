package com.concurrent.jdk;

import java.util.concurrent.Phaser;

public class PhaserTest {

    static Phaser phaser = new Phaser(7);

    static void givehongbao() {
        System.out.println(Thread.currentThread().getName() + "给红包");
        phaser.arriveAndAwaitAdvance();
    }

    static void chixi() {
        System.out.println(Thread.currentThread().getName() + "吃酒席");
        phaser.arriveAndAwaitAdvance();
    }


    static void leave() {
        System.out.println(Thread.currentThread().getName() + "离场");
        phaser.arrive();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                givehongbao();
                chixi();
                leave();
            }).start();
        }
    }
}
