package com.concurrent.jdk.forkjoinpool;

import java.util.concurrent.RecursiveAction;

public class RecursiveTask1 extends RecursiveAction {

    int start,end;

    static final int MAXNUM = 5000;

    public RecursiveTask1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end-start>MAXNUM) {
            int mid = start+(end-start)/2;
            RecursiveTask1 task1 = new RecursiveTask1(start,mid);
            RecursiveTask1 task2 = new RecursiveTask1(mid,end);
            task1.fork();
            task2.fork();
        }
        else {
            int sum = 0;
            for(int i=start;i<end;i++) {
                sum++;
            }
            System.out.println(sum);
        }
    }
}
