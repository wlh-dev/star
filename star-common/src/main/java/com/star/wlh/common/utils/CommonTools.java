package com.star.wlh.common.utils;


import org.openjdk.jol.vm.VM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;

public class CommonTools {
    private static final Logger logger = LoggerFactory.getLogger(CommonTools.class);

    public static void main(String[] args) {
        System.out.println(VM.current().details());
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
    }

    public static void print(Integer[] args) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (int i = 0; i < args.length; i++) {
            stringJoiner.add(String.valueOf(args[i]));
        }
        logger.info(stringJoiner.toString());
    }
}
