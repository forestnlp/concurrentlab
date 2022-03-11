package com.concurrent.basic.ch02;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:39
 * @Description: com.concurrent.basic.ch02
 * @version: 1.0
 */
public class FutureTaskThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        return LocalDateTime.now().toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //先包住一层
        FutureTask<String> task = new FutureTask(new FutureCallableThread());
        //再包住一层
        Thread t1 = new Thread(task);
        t1.start();
        System.out.println(task.get());
    }
}
