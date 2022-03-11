package com.concurrent.basic.ch02;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @Auther: 刘杰
 * @Date: 2022/3/8 - 03 - 08 - 13:30
 * @Description: com.concurrent.basic.ch02
 * @version: 1.0
 */
public class FutureCallableThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        return LocalDateTime.now().toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new FutureCallableThread());
        String str = future.get();
        System.out.println(str);
        executorService.shutdown();
    }
}
