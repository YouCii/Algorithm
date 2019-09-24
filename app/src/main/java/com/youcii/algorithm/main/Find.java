package com.youcii.algorithm.main;

/**
 * 查找算法.
 */
public class Find {

    public static void main(String[] args) {
        int[] array = new int[]{-1, 2, 3, 4, 6, 111};
        System.out.print("\n顺序查找: " + orderSearch(array, 3));
        System.out.print("\n二分查找: " + binarySearch(array, 3));
    }

    /**
     * 顺序查找
     * <p>
     * 时间复杂度: O(n)
     *
     * @return 目标position, 没有找到时 return -1
     */
    private static int orderSearch(int[] array, int target) {
        if (array == null || array.length < 1) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (target == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * <p>
     * 前置条件: 已排序
     * 时间复杂度: O(logn)
     *
     * @param array 已完成自小至大排序
     * @return 目标position, 没有找到时 return -1
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

}
