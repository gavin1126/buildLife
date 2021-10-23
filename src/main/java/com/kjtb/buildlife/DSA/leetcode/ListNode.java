package com.kjtb.buildlife.DSA.leetcode;

/**
 * @Author Dr d
 * @Date 2021-06-22
 **/
public class ListNode {
    public int val;
    public ListNode prev;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(ListNode prev, int val, ListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
