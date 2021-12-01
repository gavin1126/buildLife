package com.kjtb.buildlife.DSA.leetcode.dynamicProgram;

import java.util.HashMap;

/**
 * DP 要素
 * 1 重叠子问题
 * 2 最优子结构：
 * #    要符合「最优子结构」，子问题间必须互相独立
 * 3 状态转移方程
 * #    可通过数学归纳法列状态转移方程, 通过更小规模的问题，推演 （根据上一个值推这个值）
 * <p>
 * 一个可用的思维框架，辅助你思考状态转移方程：
 * 明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case（退出条件）。
 * <p>
 * dp_memo,dp_table
 * <p>
 * https://www.bookstack.cn/read/fucking-algorithm/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%B3%BB%E5%88%97-%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E8%AF%A6%E8%A7%A3%E8%BF%9B%E9%98%B6.md
 *
 * @Author Dr d
 * @Date 2021-06-30
 **/
public class DP101 {
    public static void main(String[] args) {
        DP101 t = new DP101();
        int fib = t.fib(4);

        System.out.println();
    }


    int ans = Integer.MAX_VALUE;
    int[] coins = {1, 2, 5};
    // dp[]大小不确定时，可以用map代替
    HashMap<Integer, Integer> map = new HashMap<>();

    /* 最少需要几枚硬币凑出这个金额 coins={1,2,5} amount=11,res=3
    状态：
    dp含义 y=f(x) x代表目标金额，y代表最少的硬币数
    转移方程 dp[i] = min(res,dp[i-1]+1)
    baseCase:
        dp[i]= 0; if i=0
        dp[i]= -1; if i<0
        dp[i]= min(dp[i],dp[i-1]+1); if i>0

    v1 without dp
     */
    int coinChangeV1(int[] coins, int amount) {
        for (int coin : coins) {
            int rest = amount - coin;
            if (rest == 0) return 0;
            if (rest < 0) return -1;
            ans = Math.min(ans, coinChangeV1(coins, rest));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }



    /*
    dp-memo

    dp-table
    point：赋值table，传递 table,可以设置cap=n+1
     */
    int fib2(int t) {
        if (t < 3) {
            return 1;
        }
        //记住前两项的值
        int item1 = 1;
        int item2 = 1;

        return 1;
    }

    int fib(int t) {
        if (t < 3) {
            return 1;
        }
        int[] arr = new int[t + 1];
        arr[1] = 1;
        arr[2] = 1;
        helper(t, arr);
        return arr[t];
    }

    void helper(int t, int[] aux) {
        for (int i = 3; i <= t; i++) {
            aux[i] = aux[i - 1] + aux[i - 2];
        }
    }


}
