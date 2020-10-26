package com.concurrent.jdk;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentContainerTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for(int i=0;i<1000;i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
