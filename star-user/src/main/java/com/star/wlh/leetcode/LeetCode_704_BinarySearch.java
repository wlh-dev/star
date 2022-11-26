package com.star.wlh.leetcode;

/**
 * 二分查找
 */
public class LeetCode_704_BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 1, 2, 3, 4, 5};
        int target = 3;
        int solution = solution(nums, target);
        System.out.println(solution);
    }

    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int midIndex = left + (right - left) / 2;
            if (nums[midIndex] == target) {
                return midIndex;
            } else if (nums[midIndex] < target) {
                left = midIndex + 1;
            } else {
                right = midIndex - 1;
            }
        }
        return -1;
    }
}
