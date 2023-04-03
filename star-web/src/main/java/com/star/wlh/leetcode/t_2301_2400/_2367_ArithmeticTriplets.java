package com.star.wlh.leetcode.t_2301_2400;

public class _2367_ArithmeticTriplets {
    public static void main(String[] args) {
        int[] nums= {0,1,2,3,4,5,6,7,8,9,10};
        int diff = 1;
        int i = arithmeticTriplets(nums, diff);
        System.out.println(i);
    }

    public static int arithmeticTriplets(int[] nums, int diff) {
        int[] hash = new int[201];
        int res = 0;
        for (int num : nums) {
            if (num < diff || hash[num - diff] == 0) {
                hash[num] = 1;
            } else if (hash[num - diff] == 1) {
                hash[num] = 2;
            } else {
                res++;
                hash[num] = 2;
            }
        }
        return res;
    }
}
