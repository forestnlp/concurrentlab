package com.concurrent.jdk;

public class StaticProxyTest {
    static class T implements R{
        R target = null;

        public T(R target) {
            this.target = target;
        }

        @Override
        public void run() {
            before();
            target.run();
            after();
        }

        public void start(){
            System.out.println("native method calling");
            run();
        }

        void before(){};
        void after(){};
    }
    interface R{
        void run();
    }

    public static void main(String[] args) {
        T t = new T(new R() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });

        t.start();
    }
}
