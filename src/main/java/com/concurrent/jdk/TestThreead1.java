package com.concurrent.jdk;

public class TestThreead1 {

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("t1");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true)
                System.out.println("t2");
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();

        Thread t2 = new Thread(new Thread2());
        t2.start();

        while (true) {
            System.out.println("main");
        }
    }
}
