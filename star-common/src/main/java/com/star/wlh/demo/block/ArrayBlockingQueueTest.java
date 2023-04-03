package com.star.wlh.demo.block;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    //一个由数组结构组成的有界阻塞队列
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
        for (int i = 0; i < 1025; i++) {
            queue.put("queue\t"+i);
        }
    }
}
