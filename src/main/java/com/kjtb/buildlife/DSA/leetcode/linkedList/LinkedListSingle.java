package com.kjtb.buildlife.DSA.leetcode.linkedList;

import com.kjtb.buildlife.DSA.leetcode.*;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * summary
 * 1. Going through some test cases will save you time.
 * 2. Feel free to use several pointers at the same time.
 * 3. In many cases, you need to track the previous node of the current node. （difficult previous ref）
 * <p>
 * # 在调用X.next之前一定要对X做非空校验
 * # 哨兵节点
 * dummy node：The "dummy" node is used to simplify some corner cases such as a list with only one node,
 * or removing the head of the list.
 * # 适当时刻新增开关
 * # 找重复值，可以用 hashSet/hashTable
 * # 对相似问题归类，便于以后举一反三
 * # 在改变指针引用前要保留引用
 * # init > keep > stop; step0 init， step 1- n keep ，step n+1 stop；
 * <p>
 * 1. 2 runners: faster & slower ,f +=2; s+=1
 * 2. 3 pointers (naming): prev,curr,next (head, tail)
 * <p>
 * naming:
 * position:pos; size:sz; first:fst
 * loop; loopCount:lpCnt;
 * <p>
 * 疑难点
 * 如何判断边界条件：
 * 可以用 while(head != null) 做遍历条件
 * 如何交换2个指针：
 * 类似于交换2个数值？
 * 之前是 先处理特殊情况，再处理正常情况；也可以反过来想 先处理正常情况，再处理特殊情况；
 *
 * <p>
 * https://leetcode.com/explore/learn/card/linked-list/
 * In most cases, we will use the head node (the first node) to represent the whole list.
 *
 * @Author Dr d
 * @Date 2021-06-24
 **/
public class LinkedListSingle extends Base {

    ListNode head;
    ListNode tail;
    int size = 0;


    //1>2> 4>3
    /*
    https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    输入：head = [1,2,3,4]
        1>2>4>3>N
        1>2>3>4>N
          h n t d
        td >h>n> done
    输出：[2,1,4,3]  链表 从后向前赋值？
    根据返回的形式，得知得从后往前做，即类似postOrder,但从前往后做的时候，需要做什么吗
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next.next == null) {
            return head;
        }

        ListNode n = swapPairs(head.next);
        ListNode temp = n.next;
        n.next = temp.next;
        temp.next = head.next;
        head.next = temp;

        return head.next;
    }


    /*
     1>  2>3>null
         h nh
     */
    ListNode reverse2(ListNode head) {
        if (head.next == null) {
            //1th here 倒数第一层，返回了
            return head;
        }
        //1th here 执行到这里时，已经是倒数第2层/node了
        // newHead 没有变过
        ListNode newH = reverse2(head.next);
        //由下往上做,递归与栈
        head.next.next = head;
        head.next = null;
        return newH;
    }


    ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /*
    Given the head of a linked list, rotate the list to the right by k places.

    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]

    cycle solution
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode curr = head;
        ListNode tail = null;

        int size = 1;
        while (true) {
            curr = curr.next;
            size++;
            if (curr.next == null) {
                tail = curr;
                break;
            }
        }

        curr = head;
        k = k % size;
        if (k == 0) {
            return head;
        }

        for (int i = 0; i < size - k - 1; i++) {
            curr = curr.next;
        }
        ListNode newHead = curr.next;
        curr.next = null;
        tail.next = head;

        return newHead;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode curr = head;
        int size = 1;
        while (true) {
            curr = curr.next;
            size++;
            if (curr.next == null) {
                //构建环
                curr.next = head;
                break;
            }
        }
        k = k % size;
        if (k == 0) {
            return head;
        }
        curr = head;
        for (int i = 0; i < size - k - 1; i++) {
            curr = curr.next;
        }
        head = curr.next;
        //断开环
        curr.next = null;

        return head;
    }

    /*
    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
    and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.

    data out of int bound [-2^31,2^31-1]，因为有100位数字，会越界，所以，先计算和，再转换行不通。

    999
    99

    x for l1, if l1==null  x =0; else x=l1.val
    sum = x+y+carry
    carry = sum/10
    sum%10
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode curr = new ListNode(0, null);
        ListNode dummy = curr;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
//            int f = sum > 9 ? sum % 10 : sum; // == sum %10
//            carry = (sum > 9) ? 1 : 0;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10, null);
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry, null);
        }

        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, null);
        ListNode head = dummy;
        // 是否进位
        boolean isAdd1 = false;
        while (l1 != null && l2 != null) {
            int s = l1.val + l2.val;
            int f = 0;
            if (s > 9) {
                f = isAdd1 ? s % 10 + 1 : s % 10;
                isAdd1 = true;
            } else {
                f = isAdd1 ? s + 1 : s;
                isAdd1 = false;
            }
            dummy.next = new ListNode(f, null);
            dummy = dummy.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int s = isAdd1 ? l1.val + 1 : l1.val;
            int f = 0;
            if (s > 9) {
                f = isAdd1 ? s % 10 + 1 : s % 10;
                isAdd1 = true;
            } else {
                f = isAdd1 ? s + 1 : s;
                isAdd1 = false;
            }
            dummy.next = new ListNode(f, null);
            dummy = dummy.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int s = isAdd1 ? l2.val + 1 : l2.val;
            int f = 0;
            if (s > 9) {
                f = isAdd1 ? s % 10 + 1 : s % 10;
                isAdd1 = true;
            } else {
                f = isAdd1 ? s + 1 : s;
                isAdd1 = false;
            }
            dummy.next = new ListNode(f, null);
            dummy = dummy.next;
            l1 = l1.next;
            dummy.next = new ListNode(l2.val, null);
            dummy = dummy.next;
            l2 = l2.next;
        }

        return head.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //sum = sum + 10^n* l1
        // s10 = 1
        int s0 = 1;
        int n1 = 0;
        int n2 = 0;

        while (l1 != null) {
            n1 = n1 + s0 * l1.val;
            s0 = s0 * 10;
            l1 = l1.next;
        }

        s0 = 1;
        while (l2 != null) {
            n2 = n2 + s0 * l2.val;
            s0 = s0 * 10;
            l2 = l2.next;
        }

        int n3 = n1 + n2;

        ListNode dummy = new ListNode(-1, null);
        ListNode head = dummy;
        if (n3 == 0) {
            return dummy;
        }
        while (n3 > 0) {
            ListNode newN = new ListNode(n3 % 10, null);
            dummy.next = newN;
            dummy = dummy.next;

            n3 /= 10;
        }

        return head.next;
    }

    /*
    Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

    Input: l1 = [1,2,4], l2 = [1,3,4]
    Output: [1,1,2,3,4,4]
    todo recursion
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0, null);
        ListNode head = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }

        while (l1 != null) {
            dummy.next = l1;
            l1 = l1.next;
            dummy = dummy.next;
        }
        while (l2 != null) {
            dummy.next = l2;
            l2 = l2.next;
            dummy = dummy.next;
        }

        return head.next;
    }

    /*
    Given the head of a singly linked list, return true if it is a palindrome.
    Input: head = [1,2,2,1]
    Output: true

    todo 2 points
     */
    public boolean isPalindrome2(ListNode head) {
        return false;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode curr = head;
        LinkedList<ListNode> stack = new LinkedList<ListNode>();

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        curr = head;
        while (curr != null && !stack.isEmpty()) {
            ListNode pop = stack.pop();
            if (pop.val != curr.val) {
                return false;
            }
            curr = curr.next;
        }

        return true;
    }

    /*
    Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
                   o eH
    Input: head = [1,2,3,4,5]
    Output:       [1,3,5,2,4]

     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return head;
        }
        //head ref for oddHead,evenHead ref even
        ListNode evenHead = head.next;

        // travel ref
        ListNode odd = head;
        ListNode even = evenHead;

        while (even != null && odd != null && even.next != null && odd.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode curr = head;
        ListNode oddTail = new ListNode(0, null);
        ListNode evenTail = new ListNode(0, null);
        ListNode oddHead = oddTail;
        ListNode evenHead = evenTail;
        int idx = 1;
        while (curr != null) {
            if (idx % 2 == 0) {
                evenTail.next = curr;
                evenTail = evenTail.next;
            } else {
                oddTail.next = curr;
                oddTail = oddTail.next;
            }

            curr = curr.next;
            idx++;
        }
        oddTail.next = evenHead.next;
        evenTail.next = null;
        return oddHead.next;
    }

    /*
    删除链表中的节点

    Input: head = [1,2,6,3,4,5,6], val = 6 /1/first/last
    Output: [1,2,3,4,5]

     case1: p 1 > 1 > 3 > 4,1
     case2: p 2 > 1 > 3 > 4,1

     之前是 先处理特殊情况，再处理正常情况；
     现在是 先处理正常情况，再处理特殊情况；
     */
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head.val == val ? head.next : head;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode curr = head;
        ListNode prev = new ListNode(0, head);
        ListNode dummy = new ListNode(0, head);

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                //edge
                if (curr == dummy.next) {
                    dummy = dummy.next;
                }
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return dummy.next;
    }


    /*
    从2th个节点开始，移到head前面

    1>2>3>4
    1<2 3<4

     */
    public ListNode reverseList(ListNode head) {
        //持有 prev,curr,next
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }

        return prev;
    }

    /*
    Intersection of Two Linked Lists
    Given the heads of two singly linked-lists headA and temp, return the node at which the two lists intersect.
    If the two linked lists have no intersection at all, return null.

        int[] b1 = {4, 1, 8, 4, 5};
        int[] b2 = {5, 6, 1, 8, 4, 5};
        todo : two point approach
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();

        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    // O(n*n)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode d1 = new ListNode(0, headA);
        ListNode d2 = new ListNode(0, headB);

        while (headA != null) {
            // otherwise, temp reach end,temp.next cause NPE
            ListNode temp = headB;
            while (temp != null) {
                if (headA == temp) {
                    return headA;
                }
                temp = temp.next;
            }
            headA = headA.next;
        }
        return null;
    }

    /*
      Remove Nth Node From End of List,
      删除倒数 Nth，即 删除正数 （len—n）th
      the key is to find the previous node

    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]
    [1,2,3,4,5]
     0,1,2,3,4
     5,4,3,2,1
     */
    public ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode f = dummy, s = dummy;
        for (int i = 0; i < n; i++) {
            f = f.next;
        }
        while (f.next != null) {
            f = f.next;
            s = s.next;
        }

        s.next = s.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode f = head, s = head;
        for (int i = 0; i < n; i++) {
            f = f.next;
        }
        if (f == null) {
            // delete head
            head = head.next;
            s.next = null;
            return head;
        }

        while (f.next != null && s != null) {
            s = s.next;
            f = f.next;
        }

        s.next = s.next.next;
        return head;
    }


    /*
    found L-n node,relink it to L-n+2 node
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode temp = head;
        int L = 0;
        while (temp != null) {
            L++;
            temp = temp.next;
        }

        temp = dummy;
        for (int i = 0, j = L - n; i < j; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        return dummy.next;
    }

    /*
    [1,2,3,4,5]
     0,1,2,3,4
     5,4,3,2,1

     fail ,没有dummy节点，真的不好处理,因为需要单独考虑边界条件
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = head;
        int idx = 0;
        while (prev != null) {
            if (idx < size - n) {
                prev = prev.next;
                idx++;
            } else {
                break;
            }
        }
        if (prev != null && prev.next != null) {
            prev.next = prev.next.next;
        }

        return head;
    }

    /*
    Linked List Cycle II
    Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

    找到环的入口点
    points:
    1.faster +=2,slower +=1, until they meet
    2.then reset f,s to beginning and meet point,when they meet again, it's the cycle's entrance.

     */
    public ListNode detectCycle(ListNode head) {
        ListNode f = head, s = head;
        boolean isCycle = false;
        while (s != null && f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (f == s) {
                isCycle = true;
                break;
            }
        }
        if (isCycle) {
            f = head;
            while (f != s) {
                s = s.next;
                f = f.next;
            }
            return f;
        }

        return null;
    }

    /*
    hashset 法，若curr.next 存在于set中，则代表有环
     */
    boolean hasCycle2(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return true;
            }
            set.add(curr);
            curr = curr.next;
        }
        return false;
    }


    /*
    双指针法

    判断条件是什么？
    2 pointers technique
    2 runners in the field,faster will meet slower finally
     */
    boolean hasCycle3(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode f = head.next, s = head;
        while (s != f) {
            if (f == null || f.next == null) {
                return false;
            }
            f = f.next.next;
            s = s.next;
        }
        return true;
    }

    boolean hasCycle(ListNode head) {
        ListNode f = head, s = head;
        while (s != null && f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                return true;
            }
        }
        return false;
    }










    /*------------------------------------------------------------------*/

    /**
     * Initialize your data structure here.
     */
    public LinkedListSingle() {
    }

    public LinkedListSingle(ListNode prev, int val, ListNode next) {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public ListNode get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode curr = head;
        if (index < (size << 1)) {
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode newN = null;
        if (head == null) {
            newN = new ListNode(null, val, null);
            head = tail = newN;
            size++;
            return;
        }
        newN = new ListNode(null, val, head);
        head.prev = newN;
        head = newN;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode newN = null;
        if (head == null) {
            newN = new ListNode(null, val, null);
            head = tail = newN;
            size++;
            return;
        }
        newN = new ListNode(tail, val, null);
        tail.next = newN;
        tail = newN;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        ListNode curr = get(index);
        if (curr == null) {
            addAtHead(val);
            return;
        }
        ListNode prev = curr.prev;
        ListNode next = curr.next;
        ListNode newNode = new ListNode(prev, val, next);
        prev.next = newNode;
        next.prev = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        ListNode curr = get(index);
        if (curr == null) {
            return;
        }
        ListNode prev = curr.prev;
        ListNode next = curr.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    ListNode array2LinkList(int[] a) {
        head = null;
        size = 0;
        for (int i = 0; i < a.length; i++) {
//            addAtHead(a[i]);
            addAtTail(a[i]);
        }
        return head;
    }

    /*
    反转指定范围的单链表,
    m,n = 2,4

    f c
    1,2,3,4,5
          p c n
    1,2,3,4,5
    1,4,3,2,5
    */
    ListNode reverse(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = null, cur = head, next = null;
        ListNode bpPred = dummy, breakPoint = cur;

        if (m == n || (head != null && head.next == null)) {
            return head;
        }

        int idx = 1;
        while (idx < n + 1) {
            if (idx < m) {
                bpPred = cur; // 1
                breakPoint = cur.next;
                cur = cur.next;
                idx++;
                continue;
            }
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

            if (idx == n) {
                bpPred.next = pre;
                breakPoint.next = cur;
            }
            idx++;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedListSingle t = new LinkedListSingle();
        t.test();

    }

    void test() {
        int[] a0 = {0, 1, 2, 3, 4, 5};
        int[] a = {1, 2, 3, 2, 1, 4};
        ListNode la = array2LinkList(a);

        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = {9, 9};

        ListNode ha = array2LinkList(a1);
        ListNode hb = array2LinkList(a2);

        ListNode reverse = reverse(ha, 2, 4);

        //combine
//        ListNode t = ha, q = hb;
//        while (t.val != 1) t = t.next;
//        while (q.val != 1) q = q.next;
//
//        t.next = q.next;

//        int i = get(4);

//        ListNode tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//        tail.next = head;

        boolean b = hasCycle(head);

//        ListNode listNode = removeNthFromEnd4(head, 5);

//        ListNode intersectionNode = getIntersectionNode(ha, hb);
//        ListNode reverseList = reverseList(la);

//        ListNode listNode = removeElements2(la, 1);
//        ListNode listNode1 = oddEvenList2(la);

//        boolean palindrome = isPalindrome(la);
//        ListNode listNode = mergeTwoLists(ha, hb);

//        ListNode listNode = addTwoNumbers3(ha, hb);

//        ListNode listNode = rotateRight(ha, 4);

//        ListNode reverser = reverse2(ha);

        ListNode listNode = swapPairs(ha);

        System.out.println();
    }


}
