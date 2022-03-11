package com.concurrent.jdk;

public class HappenBeforeTest {

    static int a = 0;
    static boolean flag = false;

    public static void main(String[] args) {

       for(int i=0;i<1000000;i++) {

           a= 0;
           flag=false;

           Thread t1 = new Thread(()->{
               a = 1;
               flag = true;
           });

           Thread t2 = new Thread(()->{
               if(flag){
                   a=1;
               }
               if(a==0)
                   System.out.println("a==0");
           });

           t1.start();

           t2.start();

           try {
               t1.join();
               t2.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
