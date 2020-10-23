package com.concurrent.jdk;

import java.util.Date;

public class DaemonThreadTest2 {

    public static void main(String[] args) {
        Thread life = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++) {
                    System.out.println("life is activing");
                }
            }
        });

        Thread god = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    System.out.println("god is blessing");
                }
            }
        });

        life.start();
        god.setDaemon(true);
        god.start();

    }
}
