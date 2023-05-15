package com.star.wlh.threadpool;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class _03_ThreadPrint {

    public static void main(String[] args) throws InterruptedException {
        //useAtomicInteger();
        //useCountDownLatch();
        useSemaphore();

    }

    private static void useSemaphore() {
        Semaphore semaphore = new Semaphore(3);
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private static void useCountDownLatch() {
        CountDownLatch latchA = new CountDownLatch(1);
        CountDownLatch latchB = new CountDownLatch(1);
        CountDownLatch latchC = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latchA.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A");
                latchB.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latchB.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("B");
                latchC.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latchC.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("C");
            }
        }).start();
        latchA.countDown();

    }

    private static void useAtomicInteger() {
        AtomicInteger order = new AtomicInteger(0);
        new Thread(() -> {
            while (true) {
                if (order.get() >= 100) {
                    System.out.println("已累加至100次字符打印  跳出循环   线程结束");
                    break;
                }
                // System.out.println("test"+  order.getAndIncrement());
                if (order.get() % 3 == 0) {
                    System.out.println("-------A------" + order.get());
                    order.getAndIncrement();
                }

            }

        }).start();
        new Thread(() -> {
            while (true) {
                if (order.get() >= 100) {
                    System.out.println("已累加至100次字符打印  跳出循环   线程结束");
                    break;
                }
                // System.out.println("test"+  order.getAndIncrement());
                if (order.get() % 3 == 1) {
                    System.out.println("-------B------" + order.get());
                    order.getAndIncrement();
                }

            }

        }).start();
        new Thread(() -> {
            while (true) {
                if (order.get() >= 100) {
                    System.out.println("已累加至100次字符打印  跳出循环   线程结束");
                    break;
                }
                // System.out.println("test"+  order.getAndIncrement());
                if (order.get() % 3 == 2) {
                    System.out.println("-------C------" + order.get());
                    order.getAndIncrement();
                }
            }

        }).start();


    }
}
