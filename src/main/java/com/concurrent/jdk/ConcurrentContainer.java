package com.concurrent.jdk;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConcurrentContainer {
    final LinkedList<Integer> container = new LinkedList<>();
    final int max = 5;

    public synchronized void put(int e) {
        while (container.size() == max) {
            try {
                this.wait();
            } catch (InterruptedException ea) {
                ea.printStackTrace();
            }
        }
        container.add(e);
        System.out.println("put:" + container);
        this.notifyAll();
    }

    public synchronized int get() {
        int e = 0;
        while (container.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException ea) {
                ea.printStackTrace();
            }
        }
        e = container.removeFirst();
        System.out.println("get:" + container);
        this.notifyAll();
        return e;
    }

    public static void main(String[] args) {
        int n = 0;
        while (true) {
            try {
                ConcurrentContainer container = new ConcurrentContainer();
                Thread[] producers = new Thread[10];
                Thread[] consumers = new Thread[10];

                for (int i = 0; i < producers.length; i++) {
                    producers[i] = new Thread(() -> {
                        container.put(1);
                    });
                }

                for (int i = 0; i < consumers.length; i++) {
                    consumers[i] = new Thread(() -> {
                        int x = container.get();
                        System.out.println(x);
                    });
                }

                for (Thread producer : producers) {
                    producer.start();
                }
                for (Thread consumer : consumers) {
                    consumer.start();
                }
            } catch (Exception e) {
                System.out.println(n);
            }
        }
    }
}
