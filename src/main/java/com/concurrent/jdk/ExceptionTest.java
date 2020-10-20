package com.concurrent.jdk;

public class ExceptionTest {

    int i=0;

    synchronized void m(){
        while (true) {
            System.out.println(Thread.currentThread().getName()+".."+i);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==10){
                int r = i/0;
            }
        }
    }

    public static void main(String[] args) {
        ExceptionTest t = new ExceptionTest();
        new Thread(()->{
            t.m();
        }).start();
        new Thread(()->{
            t.m();
        }).start();
    }
}
