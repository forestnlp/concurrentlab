package com.concurrent.jdk.lambda;

public class Test5 {
    interface I5{

        void m2(int a,int b,int c);
    }

    public static void main(String[] args) {
        I5 o = (int a, int b,int c) ->{
            System.out.println(a+b-c);
        };

        I5 o2 = (int a, int b,int c) ->
            System.out.println(a+b-c);
        ;

        I5 o3 = ( a,  b, c) ->
                System.out.println(a+b-c);
        ;



        o.m2(1,2,3);
        o2.m2(1,2,3);
        o3.m2(1,2,3);

    }
}
