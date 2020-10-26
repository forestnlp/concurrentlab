package com.concurrent.jdk;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class TimerAndTimerTaskTest {

    static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("task runs");
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(),1000l);
    }
}
