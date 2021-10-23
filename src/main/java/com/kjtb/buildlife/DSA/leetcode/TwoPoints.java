package com.kjtb.buildlife.DSA.leetcode;

/**
 * 双指针思维：
 * #    同向的快慢指针
 * #    反向的前后指针
 * #    固定宽度(窗口)的指针
 *
 * 滑动窗口：双指针高阶版
 * 滑动窗口算法的思路是这样：
 * int left = 0, right = 0;
 * while (right < s.size()) {
 *      //相当于在寻找一个「可行解」，窗口大小增大
 *     window.add(s[right]);
 *     right++;
 *     while (valid) {
 *     //优化这个「可行解」，窗口大小减小
 *         window.remove(s[left]);
 *         left++;
 *     }
 * }
 *
 * @Author Dr d
 * @Date 2021-06-30
 **/
public class TwoPoints {


    public static void main(String[] args) {
        TwoPoints t = new TwoPoints();

        System.out.println();
    }
}
