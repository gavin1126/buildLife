package com.kjtb.buildlife.Framework;

import java.util.Arrays;

/**
 * @Author Dr d
 * @Date 2021-07-28
 **/
public class TryCatchReturn {

    /*
    flow control
    指令控制流程
    当执行到try块中的return时，控制流转移至finally块，执行完之后，再返回
        在以下 3 种特殊情况下，finally 块不会被执行：
    在 try 或 finally块中用了 System.exit(int)退出程序。但是，如果 System.exit(int) 在异常语句之后，finally 还是会被执行
    程序所在的线程死亡。
    关闭 CPU。

    不建议在finally中return
    1 可能更新ans值
    2 可能抑制catch中抛出的异常

    建议使用 try-with-resource
     */
    static int test() {
        try {
            System.out.println("try");

            return 1;
        } catch (Exception e) {
            System.out.println("catch");
            return 2;
        } finally {
            System.out.println("finally");
            return 3;
        }
    }

    static int test2() {

        Integer i = new Integer(0);
        try {
            System.out.println("try");
            i = 1;
        } catch (Exception e) {
            System.out.println("catch");
            i = 2;
        } finally {
            System.out.println("finally");
            i = 3;
        }
        return i;
    }

    public static void main(String[] args) {
//        int test = test();
//        int test2 = test2();

        int[][] dim2 = new int[2][3];
        dim2 = new int[][]{{1, 3, 5}, {2, 4, 6}};
        int length = dim2.length;
        String s = Arrays.deepToString(dim2);
        System.out.println("s = " + s);

        System.out.println();
    }

}

class Foo {
    //    public  int func(int a){
    public static int func(int a) {
        return a;
    }
}

class Son extends Foo {
    public static int func(int a) {
        return a + 1;
    }

    public static void main(String[] args) {
        int func = func(2);
        System.out.println("func = " + func);
    }
}
