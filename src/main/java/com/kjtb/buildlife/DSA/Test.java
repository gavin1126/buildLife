package com.kjtb.buildlife.DSA;

import com.kjtb.buildlife.DSA.leetcode.*;

/**
 * @Author Dr d
 * @Date 2021-09-27
 **/
public class Test {


    void test() {

    }


    /*

     */
    public int[] merge(int[] a, int[] b) {
        if (a == null & b == null) { // or empty
            return null;
        }
        if (a == null) return b;
        if (b == null) return a;

        int len1 = a.length;
        int len2 = b.length;
        int[] c = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 & j < len2) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < len1) {
            c[k++] = a[i++];
        }

        while (j < len2) {
            c[k++] = b[j++];
        }

        return c;
    }



}
