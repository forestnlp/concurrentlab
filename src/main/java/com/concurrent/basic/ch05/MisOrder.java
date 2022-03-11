package com.concurrent.basic.ch05;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 15:05
 * @Description: com.concurrent.basic.ch05
 * @version: 1.0
 */
public class MisOrder {
    private static boolean flag = false;
    private static int num = 5;

    public static void main(String[] args) {
        new Thread(()->{
            while (!flag) {
                Thread.yield();
            }
            System.out.println(num);
        }).start();

        num = 42;
        flag = true;
    }
}
