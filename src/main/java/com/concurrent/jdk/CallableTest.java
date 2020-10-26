package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableTest {
    static class Worker implements Callable<Double>{

        private String stockno;

        public Worker(String stockno) {
            this.stockno = stockno;
        }

        @Override
        public Double call() throws Exception {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextDouble();
        }
    }

    public static void main(String[] args) {
        String[] stockNos = {"600812","300369","600276"};
        List<Future> res = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i=0;i<stockNos.length;i++){
           Future<Double> future = service.submit(new Worker(stockNos[i]));
           res.add(future);
        }

        System.out.println("call over");
        for(Future<Double> future:res){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        service.shutdown();
    }
}
