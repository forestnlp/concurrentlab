package com.concurrent.juc.ch10;

import java.util.concurrent.*;

public class FutureTaskTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> makecake = new Callable<String>(){
            @Override
            public String call() throws Exception {
                return "蛋糕做好了";
            }
        };

        FutureTask<String> task = new FutureTask(makecake);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(task);

        System.out.println(task.get());
    }
}
