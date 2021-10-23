package com.kjtb.buildlife.DSA.leetcode.queueStack;

/**
 * 环形思维：linkedList,queue
 * <p>
 * implementation:
 * # linkedList-based
 * # array-based
 *
 * application:
 * # BFS
 * # consumer-producer
 * #
 *
 * 非线性问题的核心：递归
 *
 * @Author Dr d
 * @Date 2021-06-28
 **/
public class Queue101 {

    //head/tail,front/rear,back,first/last,
    private Node head;
    private Node tail;

    public Queue101() {
    }

    void enqueue(Node node) {

    }


    static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }
}
