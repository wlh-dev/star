package com.star.wlh.algorithm.search;

public class BinarySearch {
    public static void main(String[] args) {
        Integer[] data = DataFactory.unrepeatedArrayFactory(100, 100, true);
        int result = binarySearch(data, 4);
        System.out.println(result);
    }

    private static int binarySearch(Integer[] nums, int target) {
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            count++;
            System.out.println("查询次数:" + count);
            int midIndex = left + (right - left) / 2;
            if (nums[midIndex] == target) {
                return midIndex;
            } else if (nums[midIndex] > target) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }
        return -1;
    }
}
