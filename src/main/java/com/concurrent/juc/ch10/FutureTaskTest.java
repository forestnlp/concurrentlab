package com.concurrent.juc.ch10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> makecake = new Callable<String>(){
            @Override
            public String call() throws Exception {
                return "蛋糕做好了";
            }
        };

        FutureTask<String> task = new FutureTask(makecake);

        new Thread(task).start();

        System.out.println(task.get());

    }
}
