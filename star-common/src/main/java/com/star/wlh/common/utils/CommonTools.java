package com.star.wlh.common.utils;


import cn.hutool.core.util.DesensitizedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonTools {
    private static final Logger logger = LoggerFactory.getLogger(CommonTools.class);

    public static void main(String[] args) {
        String email = DesensitizedUtil.email("825828826@qq.com");
        System.out.println(email);
    }

    public static void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printTimeAndCurrentThread(String tags) {
        String stringJoiner = new StringJoiner("\t|\t")
                .add(String.valueOf(Thread.currentThread().getName()))
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(tags)
                .toString();
        logger.info(stringJoiner);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newWorkStealingPool = Executors.newWorkStealingPool(2);
        ExecutorService newWorkStealingPoolWithOutParallelism = Executors.newWorkStealingPool();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService newCachedThreadPoolWithDefaultThreadFactory = Executors.newCachedThreadPool(Executors.defaultThreadFactory());


    }

    public static void print(Integer[] args) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (int i = 0; i < args.length; i++) {
            stringJoiner.add(String.valueOf(args[i]));
        }
        logger.info(stringJoiner.toString());
    }
}
