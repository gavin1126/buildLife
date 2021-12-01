package com.kjtb.buildlife.DSA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        //juc
        HashMap<Object, Object> map = new HashMap<>();
        ConcurrentHashMap<String, Integer> cmap = new ConcurrentHashMap<>();
        ReentrantLock lock = new ReentrantLock();
        CyclicBarrier barrier = new CyclicBarrier(3);
        ThreadPoolExecutor executor;


//        t.entrance1();
        t.entrance2();
    }

    /*
    给定一个数字的序列，返回其所有可能的全排列。
输入: [1,2,3]
输出:
[
[1,2,3],
[1,3,2],
[2,1,3],
[2,3,1],
[3,1,2],
[3,2,1]
]
     */
    void entrance1() {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> nums = Arrays.asList(1, 2, 3);
        AllSort(ans, nums, 0);

        System.out.println("ans = " + ans);
    }

    void AllSort(List<List<Integer>> ans, List<Integer> num, int idx) {
        if (idx >= num.size()) {
            ans.add(num);
            return;
        }
        for (int i = idx; i < num.size() - 1; i++) {
            exch(num, i, idx);
            AllSort(ans, num, idx++);
            exch(num, idx, i);
        }
    }

    void exch(List<Integer> num, int i, int j) {
        Integer i2 = num.get(i);
        Integer j2 = num.get(j);
        num.set(i, j2);
        num.set(j, i2);
    }

    // 4个线程打印10次ABCD
    void entrance2() {
        new Thread(()-> print("A",0),"A").start();
        new Thread(()-> print("B",1),"B").start();
        new Thread(()-> print("C",2),"C").start();
        new Thread(()-> print("D",3),"D").start();
    }

    int mod=0;
    ReentrantLock lock =new ReentrantLock();

    void print(String val, int idx) {
        for (int i = 0; i < 10; ) {
            lock.lock();
            if (mod % 4 == idx) {
                System.out.println(val);
                i++;
                mod++;
            }
            lock.unlock();
        }
    }

}
