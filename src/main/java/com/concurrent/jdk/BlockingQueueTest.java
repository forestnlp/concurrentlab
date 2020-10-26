package com.concurrent.jdk;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueTest {

    private static AtomicInteger id = new AtomicInteger(0);

    static class Data{
        private int id;
        private int val;

        public Data(int id,int val) {
            this.id = id;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", val=" + val +
                    '}';
        }
    }

    static class Producer implements  Runnable{
        BlockingQueue<Data> channel ;
        public Producer(BlockingQueue<Data> channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            while (true){

                Data data = null;
                try {
                    data = channel.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(data+" be consumed,channel = "+channel);
                }
        }
    }

    static class Consumer implements  Runnable{

        BlockingQueue<Data> channel;
        Random r = new Random();

        public Consumer(BlockingQueue<Data> channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            while (true)
            try {
                int iid = id.incrementAndGet();
                Data data = new Data(iid,r.nextInt(2048));
                channel.put(data);
                System.out.println(data+" be added,channel = "+channel);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Data> channel = new LinkedBlockingQueue<>();

        Thread[] producers = new Thread[6];
        Thread[] consumers = new Thread[5];

        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(new Producer(channel));
        }

        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new Consumer(channel));
        }

        for(Thread t:consumers) t.start();
        for(Thread t:producers) t.start();
    }
}
