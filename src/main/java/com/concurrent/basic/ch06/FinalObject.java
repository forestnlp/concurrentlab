package com.concurrent.basic.ch06;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/9 - 03 - 09 - 10:37
 * @Description: com.concurrent.basic.ch06
 * @version: 1.0
 */
public class FinalObject {
    private static  Object o = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            synchronized (o) {
                System.out.println("t1.获得了锁，");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 我马上释放锁了");
            }
        });


        t1.start();
        Thread.sleep(1000);

        o = new Object();
        Thread.sleep(1000);

        Thread t2 = new Thread(()->{
            synchronized (o) {
                System.out.println("t2.获得了锁，");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2.我马上释放锁了");
            }
        });
        t2.start();
    }
}
