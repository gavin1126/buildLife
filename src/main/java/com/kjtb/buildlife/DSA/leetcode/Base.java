package com.kjtb.buildlife.DSA.leetcode;

/**
 * @Author Dr d
 * @Date 2021-06-22
 **/
public class Base {

    /**
     * 交换元素后，索引位置i，j不变化，其对应的元素变化
     *
     * @param a
     * @param i
     * @param j
     */
    public void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * 面向接口编程
     *
     * @param a
     * @param i idx1
     * @param j idx2
     */
    public void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int min(int a, int b) {
        return a < b ? a : b;
    }

    public TreeNode arr2BST(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            TreeNode curr = root;
            int val = arr[i];
            TreeNode newN = new TreeNode(val);
            TreeNode temp = curr;

            //find newNode's parent
            while (curr != null) {
                temp = curr;
                if (val < curr.val) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            if (val < temp.val) {
                temp.left = newN;
            } else {
                temp.right = newN;
            }
        }
        return root;
    }

}
