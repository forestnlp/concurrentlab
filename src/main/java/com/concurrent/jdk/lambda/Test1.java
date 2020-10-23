package com.concurrent.jdk.lambda;

public class Test1 {
    /**
     * 内部类
     */
    static interface I1{
        void m();
    }
    static class C1 implements I1{
        @Override
        public void m() {
            System.out.println("mmmmm");
        }
    }

    public static void main(String[] args) {
        I1 o = new C1();
        o.m();
    }
}
