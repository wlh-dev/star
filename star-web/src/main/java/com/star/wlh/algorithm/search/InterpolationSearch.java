package com.star.wlh.algorithm.search;

public class InterpolationSearch {
    public static void main(String[] args) {
        Integer[] result = DataFactory.unrepeatedArrayFactory(1000, 100, true);
        int target = 99;
        int i = interpolationSearch(result, target);
        System.out.println(i);
    }

    private static int interpolationSearch(Integer[] nums, int target) {
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            count++;
            System.out.println("查找次数:" + count);
            int mid = left + (right - left) * (target - nums[left]) / (nums[right] - nums[left]);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
