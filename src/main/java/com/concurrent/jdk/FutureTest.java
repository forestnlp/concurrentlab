package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FutureTest {
    static class StringUtils {

        public static final int LEN = 5;

        private static final Random random = new Random();

        public static String genString(){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < LEN; i++) {
                sb.append((char)(random.nextInt(26)+65));
            }
            return sb.toString();
        }
    }

   static class Result{
        public Result(String msg) {
            this.msg = msg;
        }
        String msg;
    }

    static class Worker implements Callable<Result>{
        @Override
        public Result call() throws Exception {
            Random random = new Random();
            int i = random.nextInt(36);
            try {

                Thread.sleep(i*10);
                System.out.println("sleep "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Result(StringUtils.genString());
        }
    }

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(20);
        List<Future<Result>> lists = new ArrayList<>();
        for(int i=0;i<36;i++) {
            Future<Result> future = service.submit(new Worker());
            lists.add(future);
        }
        service.shutdown();

        System.out.println("go");
        for (int i = 0; i < 36; i++) {
            try {
                System.out.println(lists.get(i).get().msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
