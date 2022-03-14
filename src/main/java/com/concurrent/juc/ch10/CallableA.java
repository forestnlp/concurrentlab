package com.concurrent.juc.ch10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableA implements Callable<String>{

    @Override
    public String call() throws Exception {
        //模拟耗时计算
        Thread.sleep(1000);
        return "您好 ！ 蛋糕做好了";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new CallableA());
        System.out.println(future.get());
        executorService.shutdown();
    }
}
