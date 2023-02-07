package com.star.wlh.algorithm.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.StringJoiner;

public class DataFactory {
    /**
     * 生成不重复的数组
     *
     * @param maxInt 随机数范围
     * @param length 数组长度
     * @param sort   是否排序
     * @return Integer[]
     */
    public static Integer[] unrepeatedArrayFactory(int maxInt, int length, boolean sort) {
        Random random = new Random();
        HashSet<Integer> integers = new HashSet<>();
        Integer[] result = new Integer[length];
        for (int i = 0; i < length; i++) {
            while (true) {
                Integer nextInt = random.nextInt(maxInt);
                if (!integers.contains(nextInt)) {
                    integers.add(nextInt);
                    break;
                }
            }
        }
        result = integers.toArray(result);
        if (sort) {
            Arrays.sort(result);
        }
        StringJoiner stringBuffer = new StringJoiner(",");
        for (int i = 0; i < result.length; i++) {
            stringBuffer.add(result[i].toString());
        }
        System.out.println("生成数据是:" + stringBuffer);
        return result;
    }
}
