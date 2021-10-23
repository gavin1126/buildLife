package com.kjtb.buildlife.DSA.leetcode.binaryTree;

import com.kjtb.buildlife.DSA.leetcode.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树：非线性结构的基础
 * https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/534/
 * <p>
 * For each recursive function call, we only focus on the problem for the current node and call the function recursively to solve its children.
 * 关注 当前节点与其子节点
 * 相信该函数可以完成他的职责，不要纠结于细节，继续在高层想下一步。
 * 找到退出条件很重要。
 * <p>
 * conclusion:
 * When you meet a tree problem, ask yourself two questions:
 * 1. Can you determine some parameters to help the node know its answer?
 * 2. Can you use these parameters and the value of the node itself to determine what should be the parameters passed to its children?
 * If the answers are both yes, try to solve this problem using a "top-down" recursive solution.
 * view1：递归前的代码是沿着树从上往下走的，递归后的代码是沿着树从下往上走的。
 * view2：递归前的代码即最底层的 baseCase/退出条件，递归后的代码即为原路返回的更新操作。？
 * view3: 由上面的值可以推算出下面的值(由父节点推子节点)，可用top-down法；相反的话，可用bottom-up法
 * view4: top-down法相当于前序遍历，bottom-up法相当于后序遍历
 * top-down solution (like preOrder-traverse)
 * 1. return specific value for null node              //
 * 2. update the answer if needed                      // answer <-- params
 * 3. left_ans = top_down(root.left, left_params)      // left_params <-- root.val, params
 * 4. right_ans = top_down(root.right, right_params)   // right_params <-- root.val, params
 * 5. return the answer if needed                      // answer <-- left_ans, right_ans
 * <p>
 * Or, you can think of the problem in this way: for a node in a tree,
 * 1. if you know the answer of its children, can you calculate the answer of that node?
 * If the answer is yes, solving the problem recursively using a bottom up approach might be a good idea.
 * <p>
 * bottom-up solution (like postOrder-traverse)
 * 1. return specific value for null node
 * 2. left_ans = bottom_up(root.left)      // call function recursively for left child
 * 3. right_ans = bottom_up(root.right)    // call function recursively for right child
 * 4. return answers                       // answer <-- left_ans, right_ans, root.val
 *
 * @Author Dr d
 * @Date 2021-06-30
 **/
public class BinTree101 extends Base {

    void bfs6(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.println("poll.val = " + poll.val);
            if (poll.left != null) queue.add(poll.left);
            if (poll.right != null) queue.add(poll.right);
        }
    }

    //子结构： c,l,r ; l,c,r; l,r,c todo
    void postOrder6(TreeNode root) {
        boolean isVisited = false;
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }


        }

    }


    void preInOrder6(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //pre
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                root = stack.pop();
                //in
                root = root.right;
            }
        }

    }


    /*
    1 终止条件：叶节点
    2 返回上层什么：maxDepth
    3 本层做什么：check abs(left-right)
     */
    public boolean isBalanced(TreeNode root) {
        return false;
    }

    /*
    LCA Lowest Common Ancestor
    iterator approach
    map
    set

    recursion approach
    bottom-up model

    Start traversing the tree from the root node.
    If both the nodes p and q are in the right subtree, then continue the search with right subtree starting step 1.
    If both the nodes p and q are in the left subtree, then continue the search with left subtree starting step 1.
    If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's subtrees. and hence we return this common node as the LCA.
    */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val = root.val;
        int pv = p.val;
        int qv = q.val;
        if (val > pv && val > qv) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (val < pv && val < qv) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    void dictAdd(HashMap<TreeNode, TreeNode> map, TreeNode node) {
        TreeNode c = node;
        while (c != null) {
            map.put(c, c.parent);
            c = c.parent;
        }
    }

    /*
    todo
    在树中如何回退，回溯节点
     */
    void backtrace(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(" >" + root.val);
        backtrace(root.left);
        backtrace(root.right);
        System.out.print(" <" + root.val);
    }


    /*
    You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

    BFS
     */
    public TreeNode connect(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            TreeNode prev = q.poll();
            TreeNode curr = q.peek();
            if (prev != null) {
                prev.next = curr;
                if (prev.left != null) q.add(prev.left);
                if (prev.right != null) q.add(prev.right);
            } else {
                if (!q.isEmpty())
                    q.add(null);
            }
        }
        return root;
    }

    /*
    build tree by pre- in- order array
    pre: n l r n l r
    in : l n r l n r
    if root.left has child  root = root.left

    确定递归是自上而下还是自下而上：知道当前节点能否推出子节点，或者知道子节点能否推出当前节点，这道题应该是自上而下的

    1 利用preorder 找到root
    2 利用inorder 切分为左右子树

     */

    // 代表 subTree idx
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;

    public TreeNode arr2tree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[preorderIndex];
        preorderIndex++;
        TreeNode root = new TreeNode(rootVal);
        Integer rootIdx = inorderIndexMap.get(rootVal);

        root.left = arrayToTree(preorder, left, rootIdx - 1);
        root.right = arrayToTree(preorder, rootIdx + 1, right);

        return root;
    }


    /*
    approach1 recursion condition:
        1：2个子树 root.value 相等
        2：左子树的每个树 == 右子树的每个树
    approach2 iterator
        1：借助Queue，每次出队2个节点，比较相等后，
        2：分别入队其左右孩子
        3：next loop
    approach3 反转二叉树，若反转前==反转后，则相等
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);

        while (!q.isEmpty()) {
            TreeNode l = q.poll();
            TreeNode r = q.poll();
            if (l == null && r == null) continue;
            if (l == null || r == null) return false;

            if (l.val != r.val) {
                return false;
            }
            q.add(l.left);
            q.add(r.right);
            q.add(l.right);
            q.add(r.left);

        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isMirror(root.left, root.right);
    }

    boolean isMirror(TreeNode r1, TreeNode r2) {
        if (r1 == null & r2 == null) {
            return true;
        }
        if (r1 == null || r2 == null) {
            return false;
        }
        return r1.val == r2.val
                && isMirror(r1.left, r2.right)
                && isMirror(r1.right, r2.left);
    }

    // 反转二叉树, 交换左右子树
    TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;

// another solution
//        Node temp = root.left;
//        root.left = root.right;
//        root.right = temp;
//        invertTree(root.left);
//        invertTree(root.right);
        return root;
    }


    /*
    Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
    分解子问题，
    抽离出子结构；考虑base case，退出条件，最原子的结构，最小的问题

     */
    boolean pathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        target -= root.val;

        if (root.left == null && root.right == null && target == 0) {
            return true;
        }

        boolean l = pathSum(root.left, target);
        boolean r = pathSum(root.right, target);
        return l || r;
    }

    //bottom-up  max = Max(left.dep ,right.dep)+1
    int rez = 0;

    int maxD2(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int l = maxD2(node.left);
        int r = maxD2(node.right);
        return max(l, r) + 1;
    }

    //top down
    int maxD(TreeNode node, int height) {
        if (node == null) {
            return 0;
        }
        if (node.right == null && node.left == null) {
            return max(rez, height);
        }

        int l = maxD(node.left, height + 1);
        int r = maxD(node.right, height + 1);
        rez = max(l, r);
        return rez;
    }


    /*
    top-down solution 要考虑的问题：
    您能确定一些参数来帮助节点知道它的答案吗？
    您可以使用这些参数和节点本身的值来确定应该传递给它的子节点的参数吗？

    要设计的参数；和传递给子结构的参数
     */
    int answer = 0;

    void maxDepth(TreeNode treeNode, int depth) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.left == null && treeNode.right == null) {
            answer = max(answer, depth);
        }
        maxDepth(treeNode.left, depth + 1);
        maxDepth(treeNode.right, depth + 1);
    }

    /*
     answer = max(l,r)+1
     bottom-up
     postOrder
     */
    int maxDepth2(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int l = maxDepth2(treeNode.left);
        int r = maxDepth2(treeNode.right);
        return max(l, r) + 1;
    }

    /*
    DFS：Depth First Search
        1
       / \
      2   3
     / \
    4   5

    traverse recursively

     */
    <E> void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        visit(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    <E> void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        //暂存左子树
        inOrder(root.left);
        visit(root);
        inOrder(root.right);
    }

    <E> void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        //暂存左子树和右子树
        postOrder(root.left);
        postOrder(root.right);
        visit(root);
    }

    /*
    traverse iterable

     */
    void preOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                visit(root);
                // stash for later usage,for loop it's right
                stack.push(root);
                root = root.left;
            }
            //一路向左，直到最左端为空
            //若栈为空，则退出，否则弹出，并把弹出的右节点赋给 root
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    void inOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                visit(root);
                root = root.right;
            }
        }
    }

    /*
        重点：
    得校验lastVisited
    巧用stack.peek;
        1
       / \
      2   3
     / \
    4   5
       /
      6
6
5
//4
2
1
     */
    void postOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode lastVisited = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == lastVisited) {
                    visit(peek);
                    lastVisited = peek;
                    stack.pop();
                } else {
                    // peek.right == lastVisited
                    root = peek.right;
                }
            }
        }
    }

    /*
    breadth-first-search
    fixme 思路：
    不要总想着上一步全部完成了，再进行下一步，而是可以不断迭代的，即：上一步，下一步；上一步，下一步；不断交替迭代
    升级线性思维，单调思维，学习多元化，发散思维，多线程思维，工作如此，人生亦如此!

    先写出整体框架架子，再去考虑细节，而不要陷入下层细节不可自拔

    这里，BFS的思路是，通过不断入队，出队，来保证合理的遍历顺序，上层last Node之后，能够得到下层first node
    1
   / \
  2   3
 / \   \
4   5   6

1 n 2 3 n 4 5 6 n

Q1：如何求节点层数
** A1：不必求层数，可以在每一层末尾增加一个标识：null

     */
    List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            if (poll != null) {
                ans.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            } else {
                if (!queue.isEmpty()) {
                    queue.add(null);//add level identify
                }
                res.add(ans);
                ans = new ArrayList<>();
            }
        }
        return res;
    }

    void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            visit(poll);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    void visit(TreeNode treeNode) {
        System.out.print(treeNode.val + " , ");
    }


    public BinTree101() {
    }


    public static void main(String[] args) {
        BinTree101 t = new BinTree101();
        int[] a = {4, 2, 3, 1, 5, 6};
        int[] a1 = {1, 2, 3, 4};
        TreeNode node = t.arr2BST(a);

//        t.preOrder(node);
//        t.preOrder2(node);
//        System.out.println();
//        t.inOrder(node);
//        t.inOrder2(node);
//        System.out.println();
//        t.postOrder(node);
//        t.postOrder2(node);

//        t.bfs(node);
//        List<List<Integer>> lists = t.levelOrder(node);
//        System.out.println("lists = " + lists);

//        t.maxDepth(node, 1);
//        int i = t.maxDepth2(node);
//        boolean b = t.pathSum(node, 6);

        int top = t.maxD(node, 1);
        int down = t.maxD2(node);

        TreeNode treeNode1 = t.invertTree(node);

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode tNode = t.arr2tree(preorder, inorder);

//        TreeNode connect = t.connect(tNode);

        t.backtrace(tNode);
        List<Object> objects = new ArrayList<>();

        System.out.println();
    }
}
