package com.concurrent.jdk;

public class DoubleCheckTest {

    static class Data{
        int val;

        public Data(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "val=" + val +
                    '}';
        }
    }

    static class DCL {
        private static volatile Data o;

        public static void checkandset() {
            if (o == null) {
                synchronized (DCL.class) {
                    //if (o == null)
                        o = new Data(10);
                }
            }
        }

        public static void print() {
            if(o!=null)
                if(o.val!=10)
                    System.out.println(o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            new Thread(() -> {
                DCL.checkandset();
                DCL.print();
            }).start();
        }
    }
}
