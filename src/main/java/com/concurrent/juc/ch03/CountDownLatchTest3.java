package com.concurrent.juc.ch03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest3 implements Runnable {
    CountDownLatch latch = new CountDownLatch(300);

    @Override
    public void run() {
        //step1
        dosomething(1);
        latch.countDown();

        //step2
        dosomething(2);
        latch.countDown();

        //step3
        dosomething(3);
        latch.countDown();


        dosomethingVeryLong();
    }

    public void dosomething(int taskid){
        System.out.println("Thread "+Thread.currentThread().getName()+" is doing sth" + taskid);
    }

    public void dosomethingVeryLong(){
        try {
            Thread.sleep(1000*new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sth very hard completed");
    }

    public void moveforward() {
        try {
            latch.await();
            System.out.println("100 tasks done,move ..");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        CountDownLatchTest3 test = new CountDownLatchTest3();

        for(int i=0;i<100;i++) {
            new Thread(test).start();
        }

        test.moveforward();
    }

}
