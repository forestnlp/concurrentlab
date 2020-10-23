package com.concurrent.jdk.lambda;

public class Test3 {
    interface I3{
        void m();
    }

    /**
     * 匿名内部类
     * @param args
     */
    public static void main(String[] args) {
        I3 o = new I3() {
            @Override
            public void m() {
                System.out.println("mmm");
            }
        };
        o.m();
    }
}
