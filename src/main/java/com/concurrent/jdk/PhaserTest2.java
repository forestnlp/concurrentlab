package com.concurrent.jdk;

import java.util.concurrent.Phaser;

public class PhaserTest2 {

    static class ExamPhaser extends Phaser {

        public ExamPhaser(int parties) {
            super(parties);
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("step1，初试");
                    return false;
                case 1:
                    System.out.println("step2，笔试");
                    return false;
                case 2:
                    System.out.println("step3，殿试");
                    return true;
                default:
                    return true;
            }
        }
    }

    static Phaser phaser = new ExamPhaser(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始做");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "开始面");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "开始舔");
                phaser.arriveAndAwaitAdvance();
            }).start();
        }
    }
}
