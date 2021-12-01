package com.kjtb.buildlife.DSA.leetcode.sort;

import com.kjtb.buildlife.DSA.leetcode.*;

/**
 * sort category
 * 比较交换排序
 * 基数排序
 * 计数排序
 * <p>
 * 1 insert sort
 * 2 bubble sort
 * 3 selection sort / shell sort
 * 4 merge sort
 * 5 quick sort
 * 6 counting sort
 * 7 radix sort
 * <p>
 * jdk sort
 * insert
 * merge
 * quick
 * counting
 * <p>
 * 总结
 * 1 不熟悉时，先画图
 * 2 不好理解时，先多设置几个变量，细化结构
 * 3 思路有了以后，要自己实现，再参考最佳实践，要有多个优化版本
 * 4 care about: edge case
 *
 * @Author Dr d
 * @Date 2021-06-23
 **/
public class Sort101 extends Base {

    // eg 4,2,1,3
    int[] quickS(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return a;
        }
        int pivot = parti(a, lo, hi);
        quickS(a, lo, pivot - 1);
        quickS(a, pivot + 1, hi);
        return a;
    }

    //子区间
    int parti(int[] a, int lo, int hi) {
        int x = a[hi];
        int i = lo - 1;//代表 <x 的区间右边界, 小于区间的上界
        for (int j = lo; j < hi; j++) {
            if (a[j] < x) {
                i++;
                exch(a, i, j);
            }
        }
        exch(a, i + 1, hi);
        return i + 1;
    }

    /**
     * 分治思想 divide and conquer
     *
     * @param a
     * @param lo
     * @param hi
     */
    void quickSort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = partition(a, lo, hi);
        quickSort(a, lo, pivot - 1);
        quickSort(a, pivot + 1, hi);
    }

    /**
     * ---------i           j
     * +----------------------------------------------------------+
     * | < x     |  >x       |    ?    | =x |
     * +----------------------------------------------------------+
     *
     * @param a
     * @param lo
     * @param hi
     */
    int partition(int[] a, int lo, int hi) {
        // index for < x
        int i = lo - 1;
        int x = a[hi];
        for (int j = lo; j < hi; j++) {
            if (a[j] <= x) {
                i++;
                exch(a, i, j);
            }
        }
        exch(a, i + 1, hi);
        return i + 1;
    }

    /**
     * a   [  ]
     * tmp [  ]
     *
     * @param a
     */
    void counting(int[] a) {

    }

    /**
     * @param a
     * @param aux   辅助数组
     * @param start
     * @param end
     */
    void mergeSort(int[] a, int[] aux, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(a, aux, start, mid);
        mergeSort(a, aux, mid + 1, end);

        int s1 = start;
        int s2 = mid + 1;
        int k = start;
        while (s1 <= mid && s2 <= end) {
            aux[k++] = a[s1] < a[s2] ? a[s1++] : a[s2++];
        }
        while (s1 <= mid) {
            aux[k++] = a[s1++];
        }
        while (s2 <= end) {
            aux[k++] = a[s2++];
        }
        for (int i = start; i <= end; i++) {
            a[i] = aux[i];
        }

    }

    /**
     * point
     * +----------------------------------------------------------+
     * | sorted area |    ?     |
     * +----------------------------------------------------------+
     * {3, 5, 1, 4, 2, 4, 3};
     *
     * @param a
     */
    void insert(int[] a) {

        for (int i = 1; i < a.length; i++) {
            /*
             a[j] is the ele tobe insert
             交换后，索引i，j并不变化，而是其对应的元素变化
             */
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    exch(a, j, j - 1);
                }
            }
        }
    }

    void selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            exch(a, min, i);
        }
    }

    void bubble(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    exch(a, i, j);
                }
            }
        }
    }


    public static void main(String[] args) {
        Sort101 t = new Sort101();

        int[] a = {5, 1, 4, 2, 4, 3};
        int[] aux = new int[a.length];
//        t.bubble(a);
        t.selection(a);
//        t.insert(a);
//        t.quickSort(a, 0, 5);

//        t.mergeSort(a, aux, 0, 5);


        System.out.println();
    }

}
