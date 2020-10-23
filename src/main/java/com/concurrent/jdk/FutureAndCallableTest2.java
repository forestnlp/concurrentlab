package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class FutureAndCallableTest2 {
    static class Calculator implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return (int)System.currentTimeMillis()%1024;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = new ArrayList<>();

        for(int i=0;i<10;i++) {
            Future<Integer> future =cachedThreadPool.submit(new Calculator());
            futures.add(future);
        }

        System.out.println("ok right now do sth else Thread sleep 10sec");

        Thread.sleep(10000);

        for (Future<Integer> future:futures){
            System.out.println(future.get());
        }
    }
}
