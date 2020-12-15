package com.concurrent.jdk;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

public class AqsImpTest {

    static class SynLock extends AbstractQueuedSynchronizer {


    }


    public static void main(String[] args) {
        Executors.newCachedThreadPool();
    }

}
