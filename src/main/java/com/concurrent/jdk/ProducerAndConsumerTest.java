package com.concurrent.jdk;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Producer implements Runnable {

    List<Integer> queue;
    int maxsize = 5;
    Random random = new Random();

    public Producer(List<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxsize) {
                    try {
                        System.out.println("full ,producer wait");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int r = random.nextInt(200);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(r);
                System.out.println("produce an element = " + r + " queue=" + queue);
                queue.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {

    List<Integer> queue;

    public Consumer(List<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("empty ,consumer wait");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int r = queue.get(0);
                queue.remove(0);
                System.out.println("consume an element = " + r + " queue=" + queue);
                queue.notifyAll();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class ProducerAndConsumerTest {


    public static void main(String[] args) {
        List<Integer> queue = new LinkedList<Integer>();

        Thread[] producers = new Thread[3];
        Thread[] Consumers = new Thread[3];

        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(new Producer(queue));
            producers[i].start();
        }

        for (int i = 0; i < Consumers.length; i++) {
            Consumers[i] = new Thread(new Consumer(queue));
            Consumers[i].start();
        }
    }
}
