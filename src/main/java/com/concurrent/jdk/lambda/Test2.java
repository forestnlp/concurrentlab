package com.concurrent.jdk.lambda;

public class Test2 {
    interface I2{
        void  m();
    }

    /**
     * 方法内部类
     * @param args
     */
    public static void main(String[] args) {
        class C2 implements I2{
            @Override
            public void m() {
                System.out.println("mmm");
            }
        };

        I2 o = new C2();
        o.m();
    }
}
