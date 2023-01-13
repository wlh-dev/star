package com.star.wlh.algorithm.sort;


import com.star.wlh.algorithm.search.DataFactory;
import com.star.wlh.common.utils.CommonTools;

public class QuickSort {

    public static void main(String[] args) {
        Integer[] nums = DataFactory.unrepeatedArrayFactory(100, 10, false);
        quickSort(nums);
        CommonTools.print(nums);

    }


    public static void quickSort(Integer[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(Integer[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 将数组区分,并获取中间值的下标
        int mid = partition(nums, left, right);
        // 对左侧进行快速排序
        quickSort(nums, 0, mid - 1);
        //对右边区域进行快速排序
        quickSort(nums, mid + 1, right);

    }


    private static int partition(Integer[] nums, int left, int right) {
        // 选取pivot为当前最左侧
        int pivot = nums[left];
        // 开始节点为左侧第二位
        int startIndex = left;
        // 结束节点为最右侧
        int endIndex = right;
        while (startIndex < endIndex) {
            // 从右向左寻找第一个小于pivot的值
            while (startIndex < endIndex && nums[endIndex] > pivot) {
                endIndex--;
            }
            if (startIndex < endIndex) {
                swap(nums, startIndex, endIndex);
                startIndex++;
            }
            while (startIndex < endIndex && nums[startIndex] <= pivot) {
                startIndex++;
            }
            if (startIndex < endIndex) {
                swap(nums, startIndex, endIndex);
                endIndex--;
            }
        }
        return startIndex;
    }

    private static void swap(Integer[] nums, Integer left, Integer right) {

        Integer temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
