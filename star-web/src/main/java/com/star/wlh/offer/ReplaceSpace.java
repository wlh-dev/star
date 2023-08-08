package com.star.wlh.offer;

public class ReplaceSpace {
    public static void main(String[] args) {

    }

    /**
     * 替换空格
     *
     * @param s 输入字符串
     * @return StringBuilder sb 输出结果
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == ' ') sb.append("%20");
            else sb.append(c);
        }
        return sb.toString();
    }
}
