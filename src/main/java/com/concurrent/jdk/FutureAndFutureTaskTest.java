package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FutureAndFutureTaskTest {

    public static void futureModel1(){
        ExecutorService service = Executors.newCachedThreadPool();

        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return new Random().nextInt(1024);
            }
        });

        try {
            System.out.println(new Date());
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }

    public static void futuremodel2(){
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<>();
        for(int i=0;i<1000;i++){
            Future<Integer> future = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(5000);
                    return new Random().nextInt(1024);
                }
            });

            futureList.add(future);
        }

        for(Future<Integer> future:futureList)
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        service.shutdown();
    }

    public static void futureTaskModel1(){
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return new Random().nextInt(1024);
            }
        });
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(task);
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

    public static void futureTaskModel2(){

        ExecutorService service = Executors.newCachedThreadPool();
        List<FutureTask<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(5000);
                    return new Random().nextInt(1024);
                }
            });
            service.submit(task);
            list.add(task);
        }
        for(FutureTask<Integer> task:list){
            try {
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        service.shutdown();
    }


    public static void main(String[] args) {

        futureTaskModel2();

    }

}
