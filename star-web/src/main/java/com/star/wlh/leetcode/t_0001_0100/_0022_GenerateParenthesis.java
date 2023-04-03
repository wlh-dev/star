package com.star.wlh.leetcode.t_0001_0100;

import java.util.*;

public class _0022_GenerateParenthesis {
    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);

    }

    public static List<String> generateParenthesis(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }
        Set<String> set = new HashSet<>();
        for (String str : generateParenthesis(n - 1)) {
            for (int i = 0; i <= str.length() / 2; i++) {
                set.add(str.substring(0, i) + "()" + str.substring(i));
            }
        }
        return new ArrayList<>(set);
    }
}
