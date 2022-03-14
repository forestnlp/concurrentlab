package com.concurrent.basic.ch08;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/14 - 03 - 14 - 14:07
 * @Description: com.concurrent.basic.ch08
 * @version: 1.0
 */
public class ThreadLocalTest1 {
    private static ThreadLocal<String> thl1 = new ThreadLocal<>();
    private static ThreadLocal<Integer> thl2 = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            thl1.set("abc");
            thl2.set(12);
            System.out.println(thl1.get()+","+ thl2.get());
            thl1.remove();
            thl2.remove();
        });

        Thread t2 = new Thread(()->{
            System.out.println(thl1.get()+","+ thl2.get());
        });

        t1.start();
        t2.start();
    }
}
