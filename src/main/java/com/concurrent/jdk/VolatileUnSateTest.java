package com.concurrent.jdk;

public class VolatileUnSateTest {

    static class Web12306 implements Runnable{
        private volatile int ticketNum = 10;

        @Override
        public void run() {
            if(ticketNum<=0) return;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" sales :"+ticketNum--);
        }
    }

    public static void main(String[] args) {
        Web12306 web = new Web12306();
        for(int i=0;i<20;i++) {
            new Thread(web).start();
        }
    }
}
