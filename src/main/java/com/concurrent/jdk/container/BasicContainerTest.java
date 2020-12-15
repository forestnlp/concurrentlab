package com.concurrent.jdk.container;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BasicContainerTest {
    public static void main(String[] args) {

        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        System.out.println(Arrays.toString(deque.toArray()));
        System.out.println(deque);

    }
}
