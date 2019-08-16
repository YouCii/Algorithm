package com.youcii.algorithm;

import java.util.Arrays;

/**
 * 复习较为基础/较为重要的算法
 */
public class Class2 {

    public static void main(String[] args) {
        int[] array1 = new int[]{2, 5, 2, 4, 111, 6, -1};
        bubbleSort(array1);
        System.out.print("\n冒泡排序: " + Arrays.toString(array1));
        System.out.print("\n二分查找: " + binarySearch(array1, 9, 0, array1.length - 1));

        int[] array2 = new int[]{2, 111, 4, 6, 2, 5, -1};
        quickSort(array2, 0, array2.length - 1);
        System.out.print("\n快速排序: " + Arrays.toString(array2));
    }

    /**
     * 查找:
     * <p>
     * 二分查找
     * 前置条件: 已排序
     *
     * @param array 已完成自小至大排序
     * @return 目标position
     */
    private static int binarySearch(int[] array, int target, int start, int end) {
        if (array == null || array.length < 1 || array[array.length - 1] < array[0]
                || start < 0 || start >= array.length || end < 0 || end >= array.length
                || start > end) {
            return -1;
        }

        if (start == end) {
            if (array[start] == target) {
                return start;
            } else {
                return -1;
            }
        }

        int middle = (end + start) >> 1;
        if (array[middle] > target) {
            return binarySearch(array, target, start, middle - 1);
        } else if (array[middle] < target) {
            return binarySearch(array, target, middle + 1, end);
        } else {
            return middle;
        }
    }

    /**
     * 排序:
     * <p>
     * 最简单的冒泡排序
     * 时间复杂度: O(n²)
     * 空间复杂度: O(1)
     * <p>
     * 自小至大排序
     */
    private static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int cache;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    cache = array[j];
                    array[j] = array[i];
                    array[i] = cache;
                }
            }
        }
    }

    /**
     * 排序:
     * <p>
     * 快速排序
     * 时间复杂度: O(n*logn) -> O(n²)
     * 空间复杂度: O(logn)
     * 思路: 分治法
     * <p>
     * 自小至大排序
     */
    private static void quickSort(int[] array, int start, int end) {
        if (array == null || array.length < 2 || start >= end) {
            return;
        }
        int middle = partition(array, start, end);
        quickSort(array, start, middle - 1);
        quickSort(array, middle + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (start < 0 || end < 0 || start >= array.length || end >= array.length || start > end) {
            throw new IndexOutOfBoundsException();
        }

        int middle = getMiddleInThree(array, start, (start + end) / 2, end);
        int keyValue = array[middle];
        array[middle] = array[start];
        array[start] = keyValue;

        while (start < end) {
            while (end > start && array[end] >= keyValue) {
                end--;
            }
            if (start == end) {
                return start;
            }
            array[start] = array[end];
            array[end] = keyValue;

            while (start < end && array[start] <= keyValue) {
                start++;
            }
            if (start == end) {
                return start;
            }
            array[end] = array[start];
            array[start] = keyValue;
        }
        return start;
    }

    private static int getMiddleInThree(int[] array, int num0, int num1, int num2) {
        if (num0 < 0 || num1 < 0 || num2 < 0 || num0 >= array.length || num1 >= array.length || num2 >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        if (array[num0] > array[num1]) {
            if (array[num0] <= array[num2]) {
                return num0;
            } else if (array[num1] > array[num2]) {
                return num1;
            } else {
                return num2;
            }
        } else if (array[num0] < array[num1]) {
            if (array[num0] >= array[num2]) {
                return num0;
            } else if (array[num1] > array[num2]) {
                return num2;
            } else {
                return num1;
            }
        } else {
            return num0;
        }
    }


    /**
     * 排序:
     * <p>
     * 归并排序
     * 时间复杂度: 稳定O(n*logn)
     * 空间复杂度: O(n)
     * 思路: 分治法
     */
    private static String mergeSort() {
        return "";
    }

}
