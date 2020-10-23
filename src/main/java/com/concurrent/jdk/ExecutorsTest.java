package com.concurrent.jdk;

import java.util.concurrent.Executor;

public class ExecutorsTest {

    public static void main(String[] args) {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                System.out.println("hello");
            }
        };


    }

}
