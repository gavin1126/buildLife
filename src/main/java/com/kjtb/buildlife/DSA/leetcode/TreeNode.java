package com.kjtb.buildlife.DSA.leetcode;

/**
 * @Author Dr d
 * @Date 2021-06-22
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    // 右侧节点
    public TreeNode next;
    public TreeNode parent;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right, TreeNode next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}