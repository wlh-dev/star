package com.star.wlh.algorithm.sort;

import java.util.StringJoiner;

public class HeapSort {

    public void sort(int[] arr, int n) {
        //建堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        //排序
        for (int i = n - 1; i > 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void heapAdjust(int i, int length) {

    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int lson = i * 2 + 1;
        int rson = i * 2 + 2;
        if (lson < n && arr[largest] < arr[lson]) {
            largest = lson;
        }
        if (rson < n && arr[largest] < arr[rson]) {
            largest = rson;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }

    }

    private void print(int[] arr) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (int i = 0; i < arr.length; i++) {
            stringJoiner.add(String.valueOf(arr[i]));
        }
        System.out.println("打印最终结果:" + stringJoiner);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 8, 1, 4, 9, 10, 7, 16, 14};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(arr, arr.length);
        heapSort.print(arr);
    }
}
