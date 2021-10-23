package com.kjtb.buildlife.DSA.leetcode.dynamicProgram;

/**
 * 回溯算法框架：（类似与遍历决策树）
 * 解决一个回溯问题，实际上就是一个决策树的遍历过程。你只需要思考 3 个问题：
 * 1、路径：也就是已经做出的选择。
 * 2、选择列表：也就是你当前可以做的选择。
 * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
 * <p>
 * 代码方面，回溯算法的框架：
 * <p>
 * #     result =[]
 * #     def backtrack(路径,选择列表):
 * #     if满足结束条件:
 * #             result.add(路径)
 * #     return
 * #     for选择in选择列表:
 * #     做选择
 * #             backtrack(路径,选择列表)
 * #     撤销选择
 *
 * @Author Dr d
 * @Date 2021-06-30
 **/
public class Backtrace101 {

    public static void main(String[] args) {
        Backtrace101 t = new Backtrace101();

        System.out.println();
    }
}
