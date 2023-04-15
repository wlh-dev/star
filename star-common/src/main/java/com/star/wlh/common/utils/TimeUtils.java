package com.star.wlh.common.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
    private static ZonedDateTime NOW = ZonedDateTime.of(
            2023,
            1,
            1,
            0,
            0,
            0,
            0,
            ZoneId.of("Asia/Shanghai")
    );

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(NOW);
    }

}
