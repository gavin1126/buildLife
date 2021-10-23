package com.kjtb.buildlife.DSA.leetcode.sort;

import com.kjtb.buildlife.DSA.leetcode.*;

/**
 * @Author Dr d
 * @Date 2021-09-12
 **/
public class Sort102 extends Base {

    public int[] quickSort(int[] arr) {
        int len = arr.length;

        return qSort(arr, 0, len-1);
    }

    private int[] qSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return arr;
        }
        int pivot = partition(arr, lo, hi);
        qSort(arr, lo, pivot - 1);
        qSort(arr, pivot + 1, hi);
        return arr;
    }

    // [p,   <p,  >p, x ]
    private int partition(int[] arr, int lo, int hi) {
        int pivot = lo;
        int i = lo; // max idx for < pivotVal
        for (int j = lo + 1; j <= hi; j++) {
            if (arr[j] < arr[pivot]) {
                // 从未知区域发现一个小于 pivot 的值
                i++;
                exch(arr, i, j);
            }
        }
        exch(arr, pivot, i);
        return i;
    }

    public int[] msort(int[] arr) {
        return mergeSort(arr, 0, arr.length);
    }

    public int[] mergeSort(int[] arr, int lo, int hi) {
        int mid = (lo + hi) / 2;

        int[] leftSort = mergeSort(arr, lo, mid);
        int[] rightSort = mergeSort(arr, mid + 1, hi);
        return merge(leftSort, rightSort);
    }

    private int[] merge(int[] leftSort, int[] rightSort) {
        return null;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 5, 2, 3};
        Sort102 t = new Sort102();

        int[] ints = t.quickSort(a);

    }

}
