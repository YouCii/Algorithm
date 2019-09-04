package com.youcii.algorithm;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 复习较为基础/重要的算法
 */
public class Class2 {

    public static void main(String[] args) {
        int[] array1 = new int[]{2, 5, 2, 4, 111, 6, -1};
        bubbleSort(array1);
        System.out.print("\n冒泡排序: " + Arrays.toString(array1));
        System.out.print("\n二分查找: " + binarySearch(array1, 3));

        int[] array2 = new int[]{2, 11, '0', 1, -2, 5, '-'};
        quickSort(array2, 0, array2.length - 1);
        System.out.print("\n快速排序: " + Arrays.toString(array2));

        int[] array3 = new int[]{2, 11, '0', 1, -2, 5, '-'};
        mergeSort(array3, 0, array3.length - 1);
        System.out.print("\n归并排序: " + Arrays.toString(array3));

        System.out.print("\n是否包含重复字符: " + containsDuplicate("q47werx2tyu12io"));
    }

    /**
     * 二分查找:
     * <p>
     * 前置条件: 已排序
     *
     * @param array 已完成自小至大排序
     * @return 目标position
     */
    private static int binarySearch(int[] array, int target) {
        if (array == null || array.length < 1) {
            return -1;
        }
        int start = 0, end = array.length - 1;
        if (array[start] > target || array[end] < target) {
            return -1;
        }

        int middle;
        while (start <= end) {
            middle = (start + end) >> 1;
            if (array[middle] > target) {
                end = middle - 1;
            } else if (array[middle] < target) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 最简单的冒泡排序:
     * <p>
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
     * 快速排序:
     * <p>
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
        if (start < 0 || end >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        int divider = partition(array, start, end);
        quickSort(array, start, divider - 1);
        quickSort(array, divider + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        // 这里不进行array判空了, 直接抛出异常, 更有利于排查问题
        if (start > end || start < 0 || end >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        // 不直接选择头尾位置, 以防出现对基本排序的数组划分次数接近于n
        int dividerIndex = getMiddleInThree(array, start, end, (start + end) >> 1);
        int divider = array[dividerIndex];
        array[dividerIndex] = array[start];
        array[start] = divider;

        int startIndex = start, endIndex = end;
        while (startIndex < endIndex) {
            while (startIndex < endIndex) {
                if (array[endIndex] < divider) {
                    array[startIndex] = array[endIndex];
                    break;
                }
                endIndex--;
            }
            while (startIndex < endIndex) {
                if (array[startIndex] > divider) {
                    array[endIndex] = array[startIndex];
                    break;
                }
                startIndex++;
            }
        }
        array[startIndex] = divider;
        return startIndex;
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
     * 归并排序:
     * <p>
     * 时间复杂度: 稳定O(n*logn)
     * 空间复杂度: O(n)
     * 思路: 分治法
     * <p>
     * 自小至大排序
     */
    private static void mergeSort(int[] array, int start, int end) {
        if (array == null || array.length < 2 || start < 0 || end >= array.length || start >= end) {
            return;
        }
        int middle = (start + end) >> 1;
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);

        mergeSortedArray(array, middle, start, end);
    }

    /**
     * 合并 start-middle, middle+1-end 两个已排序数组
     */
    private static void mergeSortedArray(@NotNull int[] array, int middle, int start, int end) {
        if (start < 0 || end >= array.length || middle < start || middle > end) {
            throw new IndexOutOfBoundsException();
        }
        int[] cacheArray = new int[end - start + 1];
        int startIndex = start, endIndex = middle + 1, cacheIndex = 0;
        while (startIndex <= middle && endIndex <= end) {
            cacheArray[cacheIndex++] = array[startIndex] < array[endIndex] ? array[startIndex++] : array[endIndex++];
        }
        while (startIndex <= middle) {
            cacheArray[cacheIndex++] = array[startIndex++];
        }
        while (endIndex <= end) {
            cacheArray[cacheIndex++] = array[endIndex++];
        }
        while (cacheIndex > 0) {
            array[--endIndex] = cacheArray[--cacheIndex];
        }
    }

    /**
     * 给定字符串内(A-Z, a-z, 0-9)是否存在重复字符
     * 亲身经历的面试题
     */
    private static boolean containsDuplicate(String string) {
        if (string == null || string.length() < 2) {
            return false;
        }
        char[] chars = string.toCharArray();
        // 0-9对应48-57, A-Z对应65-90, a-z对应97-122, 所以申请length为123的数组
        // 初始化的数组内默认都是0
        int[] cache = new int[123];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= cache.length) {
                throw new IllegalArgumentException("字符串格式错误");
            }

            if (cache[chars[i]] == 0) {
                cache[chars[i]]++;
            } else {
                return true;
            }
        }
        return false;
    }

}
