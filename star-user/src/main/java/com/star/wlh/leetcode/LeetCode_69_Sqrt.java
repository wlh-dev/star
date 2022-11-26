package com.star.wlh.leetcode;

public class LeetCode_69_Sqrt {
    public static void main(String[] args) {
        int solution = solution(6);
        System.out.println(solution);
    }

    public static int solution(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1;
        int right = x / 2;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
