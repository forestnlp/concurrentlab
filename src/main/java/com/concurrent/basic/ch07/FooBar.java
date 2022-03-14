package com.concurrent.basic.ch07;

public class FooBar {

        private int n;

        private volatile boolean flag = true;

        public FooBar(int n) {
            this.n = n;
          }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                synchronized (this){
                    while (!flag){
                        wait();
                    }
                    printFoo.run();
                    flag=!flag;
                    notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (flag) {
                        wait();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    flag=!flag;
                    notify();
                }
            }
        }
    }