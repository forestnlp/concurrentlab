package com.concurrent.jdk;

import java.util.Date;
import java.util.concurrent.*;

public class FutureAndCallableTest {
    static class Calculator implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(10000);
            System.out.println(new Date());
            return (int)(System.currentTimeMillis()%1024);
        }
    }

    public static void main(String[] args) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        Future<Integer> result = singleThreadPool.submit(new Calculator());
        System.out.println("OK,i got the result,do sth else");

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("OK,i got the real result ");

    }
}
