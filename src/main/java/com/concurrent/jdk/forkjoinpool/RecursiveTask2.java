package com.concurrent.jdk.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class RecursiveTask2 extends RecursiveTask<Integer> {

    int start,end;
    static final int MAXNUM=1000;

    public RecursiveTask2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end-start>MAXNUM) {
            int mid = start+(end-start)/2;
            RecursiveTask2 task1 = new RecursiveTask2(start,mid);
            RecursiveTask2 task2 = new RecursiveTask2(mid,end);
            task1.fork();
            task2.fork();
            return task1.join()+task2.join();
        }
        else {
            int i = 0;
            while (start<end) {
                start++;
                i+=2;
            }
            return i;
        }
    }
}
