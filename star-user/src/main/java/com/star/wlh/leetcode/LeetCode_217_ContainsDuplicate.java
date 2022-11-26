package com.star.wlh.leetcode;

import java.util.Arrays;

public class LeetCode_217_ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 4};
        boolean b = containsDuplicate(nums);
        System.out.println(b);
    }

    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
