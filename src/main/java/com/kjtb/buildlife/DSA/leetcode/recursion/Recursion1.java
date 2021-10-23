package com.kjtb.buildlife.DSA.leetcode.recursion;

import com.kjtb.buildlife.DSA.leetcode.*;

/**
 * 递归总结
 * 递归与栈相似，先深入，再浅出(LIFO 先进后出)
 * \     /
 *  \   /
 *   \ /
 *
 * 原理：
 * # 先处理上层问题，再在下层套用上层方案
 * # 逆向思维，从上往下传递的时候，基本上没有逻辑处理，从下往上回弹的时候（从后往前），才开始处理
 *
 * <p>
 * 适应场景
 * <p>
 * https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487910&idx=1&sn=2670aec7139c6b98e83ff66114ac1cf7&chksm=fb418286cc360b90741ed54fecd62fd45571b2caba3e41473a7ea0934f918d4b31537689c664&token=910002910&lang=zh_CN#rd
 * https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/
 *
 * @Author Dr d
 * @Date 2021-06-23
 **/
public class Recursion1 extends Base {

    /*
    https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/1681/
    Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying
    the values in the list's nodes (i.e., only nodes themselves may be changed.)

    Input: head = [1,2,3,4]
    Output: [2,1,4,3]

    rule:
    1.return condition
    2.recursion condition

    steps:
    1.break down problem into smaller ones
    2.call F() recursively
    3.process the results


     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = head;
        ListNode b = head.next;

        a.next = b.next;
        b.next = a;

        // why ???
        a.next = swapPairs(a.next);
        return b;
    }


    /*
    Write a function that reverses a string. The input string is given as an array of characters s.

     */
    public void reverseString2(Character[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            int j = n - 1 - i;
            exch(s, i, j);
        }

    }

    public void reverseString(Character[] s) {
        int i = 0, j = s.length - 1;
        while (i <= j) {
            exch(s, i++, j--);
        }
    }


    public static void main(String[] args) {
        Recursion1 t = new Recursion1();

        Character[] s1 = {'h', 'e', 'l', 'l', 'o'};

        t.reverseString2(s1);

        System.out.println();
    }
}
