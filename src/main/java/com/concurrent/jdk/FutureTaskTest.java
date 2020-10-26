package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) {
        List<FutureTask<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tasks.add(new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(2000));
                    return new Random().nextInt(100);
                }
            }));
        }

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < tasks.size(); i++) {
            service.submit(tasks.get(i));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDone())
                try {
                    System.out.println(tasks.get(i).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        }
        service.shutdown();
    }
}
