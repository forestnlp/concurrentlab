package com.concurrent.jdk;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<Integer.MAX_VALUE;i++)
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("i");
            }
        });
        service.shutdown();
    }
}
