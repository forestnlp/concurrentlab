package com.concurrent.jdk.lambda;

public class Test4 {
    interface I4{
        void m();
    }

    /**
     * lambda推导
     * @param args
     */
    public static void main(String[] args) {
        I4 o = null;
        o = ()->{
            System.out.println("mmm");
        };
        o.m();
    }
}
