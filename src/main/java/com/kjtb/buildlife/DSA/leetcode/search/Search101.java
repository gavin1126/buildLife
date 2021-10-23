package com.kjtb.buildlife.DSA.leetcode.search;

import com.kjtb.buildlife.DSA.leetcode.*;

/**
 * 分类
 * 1 线性查询
 * 2 二分查找
 * 3 混合查询：二分查找+线性查找，块间有序，块内无序
 *
 * @Author Dr d
 * @Date 2021-06-23
 **/
public class Search101 extends Base {

    /**
     * @param a      sorted
     * @param target
     * @return index of the target, or null if not found
     */
    int binSearch(int[] a, int target) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (a[mid] < target) {
                hi = mid - 1;
            } else if (a[mid] > target) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Search101 t = new Search101();
        int[] a = {1, 2, 4, 5, 7, 11};
        int i = t.binSearch(a, 3);

        System.out.println();
    }


}
