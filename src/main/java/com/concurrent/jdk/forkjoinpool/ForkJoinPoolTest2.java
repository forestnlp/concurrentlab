package com.concurrent.jdk.forkjoinpool;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;

public class ForkJoinPoolTest2 {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveTask2 recursiveTask2 = new RecursiveTask2(0, 500000);
        forkJoinPool.execute(recursiveTask2);
        System.out.println(recursiveTask2.join());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
