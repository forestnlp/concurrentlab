package com.concurrent.jdk.lambda;

public class Test6 {

    interface I6{
        int add(int a,int b);
    }

    public static void main(String[] args) {
        //I6 o = (a,b)->{return a+b;};
        I6 o = (a,b)-> a+b;
        int add = o.add(1, 2);
        System.out.println(add);
    }
}
