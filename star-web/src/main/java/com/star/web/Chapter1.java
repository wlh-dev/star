package com.star.web;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chapter1 {
    private static String pattern = "\\{\\{((?:.|\\r?\\n)+?)\\}\\}";

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    }


    /**
     * 抽取输入字符串大括号中的数据
     * String rule = "/\{\{(.+?)\}\}/g";
     *
     * @param content
     */
    public static String extractInputParameter(String content, Map<String, Object> value) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        Map<Integer, String> map = new HashMap<>();
        int count = 0;
        while (matcher.find()) {
            String group = matcher.group();
            content = content.replace(group, String.valueOf(count));
            String result = group.substring(2, group.length() - 2).trim();
            map.put(count, result);
            count++;
        }
        for (String valueKey : map.values()) {
            if (!value.containsKey(valueKey)) {
                throw new RuntimeException("the params key " + valueKey + " could not be null");
            }
        }
        for (int i = 0; i < count; i++) {
            String key = map.get(i);
            Object obj = value.get(key);
            content = content.replace(String.valueOf(i), String.valueOf(obj));

        }
        return content;
    }
}
