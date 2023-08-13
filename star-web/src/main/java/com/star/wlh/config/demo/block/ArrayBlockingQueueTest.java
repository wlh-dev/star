package com.star.wlh.config.demo.block;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    //一个由数组结构组成的有界阻塞队列
    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(1024);
        for (int i = 0; i < 1024; i++) {
           queue.add("queue"+i);
        }
    }
}
