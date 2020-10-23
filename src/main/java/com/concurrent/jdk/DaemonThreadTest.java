package com.concurrent.jdk;

import java.util.Date;

public class DaemonThreadTest {

    public static void main(String[] args) {

        new Thread(()->{
            while (true)
                try {
                    Thread.sleep(100);
                    System.out.println(new Date());
                    if(System.currentTimeMillis()%11==0) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }).start();

        for(int i=0;i<100;i++) {
            int finalI = i;
            Thread t = new Thread(()->{
                System.out.println("new thread " + finalI +" start");
            });
            if(finalI==33){
                t.setDaemon(true);
            }
            t.start();
        }
    }
}
