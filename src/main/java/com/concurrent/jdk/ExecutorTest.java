package com.concurrent.jdk;

import java.util.concurrent.Executor;

public class ExecutorTest {
    public static void main(String[] args) {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        };

        executor.execute(()->{
            for(int i=0;i<100;i++)
                System.out.println("gogo");
        });
    }
}
