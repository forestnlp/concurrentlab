package com.concurrent.basic.ch02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:28
 * @Description: com.concurrent.basic.ch02
 * @version: 1.0
 */
public class UsingExecutors {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是t1线程");
            }
        });
        executorService.shutdown();
    }
}
