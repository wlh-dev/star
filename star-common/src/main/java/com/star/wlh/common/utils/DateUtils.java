package com.star.wlh.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getCurrentData() {
        return new Date(Instant.now().getEpochSecond());
    }

    public static void main(String[] args) {
        String currentTimeStr = getCurrentLongDateTimeStr();
        System.out.println(currentTimeStr);
    }

    public static String getCurrentLongDateTimeStr() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

}
