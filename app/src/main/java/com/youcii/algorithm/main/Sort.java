package com.youcii.algorithm.main;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 排序算法, 均是自小至大排序
 */
public class Sort {

    public static void main(String[] args) {
        int[] array1 = new int[]{2, 5, 2, 4, 111, 6, -1};
        bubbleSort(array1);
        System.out.print("\n冒泡排序: " + Arrays.toString(array1));

        int[] array2 = new int[]{2, 11, '0', 1, -2, 5, '-'};
        quickSort(array2, 0, array2.length - 1);
        System.out.print("\n快速排序: " + Arrays.toString(array2));

        int[] array3 = new int[]{2, 11, '0', 1, -2, 5, '-'};
        mergeSort(array3, 0, array3.length - 1);
        System.out.print("\n归并排序: " + Arrays.toString(array3));
    }

    /**
     * 最简单的冒泡排序:
     *
     * 时间复杂度: O(n²)
     * 空间复杂度: O(1)
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
     *
     * 时间复杂度: O(n*logn) -> O(n²)
     * 空间复杂度: O(logn)
     * 思路: 分治法
     *
     * 时间复杂度推算:
     *
     * 排列:
     * f(n) = partition的n次 + 两个递归即 2*f(n/2)
     * f(n/2) = n/2 + 2f(n/4)
     * f(n/4) = n/4 + 2f(n/8)
     * ....
     * f(n/(2^m)) = n/(2^m) + 2f(n/(2^(m+1)))
     *
     * 代入:
     * f(n) = n + 2f(n/2)
     * = n + 2[n/2 + 2f(n/4)] = 2n + 4f(n/4)
     * = 2n + 4[n/4 + 2f(n/8)] = 3n + 8f(n/8)
     * = m*n + 2^m*f(n/(2^m))
     *
     * 因为:
     * f(1)=1, 当 n/(2^m)=1 时, f(n/(2^m))=1; n/(2^m)=1可以得到: m=logn
     * 所以:
     * f(n) = m*n + 2^m*f(n/(2^m))
     * = logn*n + 2^logn*1 = n*logn+1
     * 所以:
     * 时间复杂度为O(n*logn)
     */
    private static void quickSort(int[] array, int start, int end) {
        if (array == null || array.length < 2 || start >= end) {
            return;
        }
        if (start < 0 || end >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        // 空间复杂度: 每次二分产生1的暂存, 二分次数约为logn次, 所以总控件复杂度为O(logn)
        int divider = partition(array, start, end);
        quickSort(array, start, divider - 1);
        quickSort(array, divider + 1, end);
    }

    /**
     * 选择某值为分界线, 把[start, end]划分为小于该值和大于该值的两部分, 并返回该值最终角标
     * 时间复杂度: O(end-start+1)
     * 空间复杂度: O(0)
     */
    private static int partition(int[] array, int start, int end) {
        // 这里不进行array判空了, 直接抛出异常, 更有利于排查问题
        if (start > end || start < 0 || end >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        // 不直接选择头尾位置, 以防出现对基本排序的数组划分次数接近于n
        int dividerIndex = getMiddleOfThree(array, start, end, (start + end) >> 1);
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

    /**
     * 返回三者之间的中间值
     */
    private static int getMiddleOfThree(int[] array, int num0, int num1, int num2) {
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
     *
     * 时间复杂度: 稳定O(n*logn)
     * 空间复杂度: O(n)
     * 思路: 分治法
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
     *
     * 时间复杂度: O(end - start + 1)
     * 空间复杂度: O(end - start + 1)
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

}
