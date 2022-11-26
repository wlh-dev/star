package com.star.wlh.common.entity.base;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class NodeTest {
    public static void main(String[] args) {
        int n = 8;
        n = n & (n - 1);
        System.out.println(n);

        // useLockSupportPrintOrder();
        //useSynchronizedAndWaitAndNotifyPrintOrder(new Object());
        //  useSingleThreadPoolPrintOrder();
        //useSemaphorPrintOrder();
        //useCyclicBarrierPrintOrder();
        //useThreadJoinPrintOrder();
        //LockSupport.park();


    }

    private static void useThreadJoinPrintOrder() {
        Thread thread1 = new Thread(() -> {
            print("1");
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            print("2");
        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            print("3");
        });
        thread3.start();


    }

    private static void useCyclicBarrierPrintOrder() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        Thread thread1 = new Thread(() -> {
            print("1");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            print("2");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            print("3");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        thread3.start();
    }

    public static void print(String args) {
        System.out.println("------------" + args);
    }

    private static void useSemaphorPrintOrder() {
        Semaphore semaphore = new Semaphore(1);
        Thread thread1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("------------1");
                semaphore.release();
            } catch (InterruptedException e) {

            }

        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("------------2");
                semaphore.release();
            } catch (InterruptedException e) {

            }

        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("------------3");
                semaphore.release();
            } catch (InterruptedException e) {

            }

        });
        thread3.start();
    }


    private static void useSingleThreadPoolPrintOrder() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(50),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> System.out.println("-------------1"));
            pool.execute(() -> System.out.println("-------------2"));
            pool.execute(() -> System.out.println("-------------3"));
        }

        pool.shutdown();
    }

    private static void useSynchronizedAndWaitAndNotifyPrintOrder(Object obj) {
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("-----------1");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread1"
        );
        thread1.start();

        Thread thread2 = new Thread(() -> {
            obj.notify();
            synchronized (obj) {
                System.out.println("----------2");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "thread2"
        );
        thread2.start();

        Thread thread3 = new Thread(() -> {
            obj.notify();
            synchronized (obj) {
                System.out.println("----------3");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                obj.notify();
            }
        }, "thread3"
        );
        thread3.start();


    }

    private static void useLockSupportPrintOrder() {
        Thread thread3 = new Thread(() -> {
            LockSupport.park();
            System.out.println("----------3");
        }, "thread3"
        );
        thread3.start();

        Thread thread2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("----------2");
            LockSupport.unpark(thread3);
        }, "thread2"
        );
        thread2.start();

        Thread thread1 = new Thread(() -> {
            System.out.println("----------1");
            LockSupport.unpark(thread2);
        }, "thread1"
        );
        thread1.start();
    }
}
