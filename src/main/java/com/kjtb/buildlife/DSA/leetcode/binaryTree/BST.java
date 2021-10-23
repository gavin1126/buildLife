package com.kjtb.buildlife.DSA.leetcode.binaryTree;

import com.kjtb.buildlife.DSA.leetcode.*;

import java.util.HashMap;

/**
 * BST:BinarySearchTree
 * 常见总结
 * 1、left < node < right 非常重要，非常重要，非常重要
 * 2、
 * <p>
 * 1、二叉树算法设计的总路线：把当前节点要做的事做好，其他的交给递归框架，不用当前节点操心。
 * 2、如果当前节点会对下面的子节点有整体影响，可以通过辅助函数增长参数列表，借助参数传递信息。
 * 3、在二叉树框架之上，扩展出一套 BST 遍历框架：
 * # void BST(TreeNode root, int target) {
 * #    if (root.val == target)
 * #        // 找到目标，做点什么
 * #    if (root.val < target)
 * #        BST(root.right, target);
 * #    if (root.val > target)
 * #        BST(root.left, target);
 * # }
 * or
 * # TreeNode BST(TreeNode root, int target) {
 * #    if (root.val == target)
 * #        // 找到目标，做点什么
 * #    if (root.val < target)
 * #        BST(root.right, target);
 * #    if (root.val > target)
 * #        BST(root.left, target);
 * #    return root;
 * # }
 * <p>
 * conclusion:
 * When you meet a tree problem, ask yourself two questions:
 * 1. Can you determine some parameters to help the node know its answer?
 * 2. Can you use these parameters and the value of the node itself to determine what should be the parameters passed to its children?
 * If the answers are both yes, try to solve this problem using a "top-down" recursive solution.
 * top-down solution (like preOrder-traverse)
 * 1. return specific value for null node
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
 * <p>
 * https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/534/
 *
 * @Author Dr d
 * @Date 2021-07-05
 **/
public class BST extends Base {

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return buildTree2(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    /*
    build by postOrder and inOrder
    inOrder:[[leftSubTree],root,[rightSubTree]]
    postOrder:[[leftSubTree],[rightSubTree],root]
     */
    public TreeNode buildTree2(int[] postorder, int[] inorder, int postStart, int postEnd, int inStart, int inEnd) {
        if (postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        Integer rootIdx = map.get(rootVal);
        int leftSubSize = rootIdx - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree2(postorder, inorder, postStart, postStart + leftSubSize - 1, inStart, rootIdx - 1);
        root.right = buildTree2(postorder, inorder, postStart + leftSubSize, postEnd - 1, rootIdx + 1, inEnd);
        return root;
    }

    /*
    preOrder:[root,[leftSubTree],[rightSubTree]]
    inOrder:[[leftSubTree],root,[rightSubTree]]
    核心：分解前序序列
     */
    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        Integer rootIdx = map.get(rootVal);
        TreeNode treeNode = new TreeNode(rootVal);
        int leftSubSize = rootIdx - inStart;
        treeNode.left = buildTree(preorder, inorder, preStart + 1, preStart + leftSubSize, inStart, rootIdx - 1);
        treeNode.right = buildTree(preorder, inorder, preStart + leftSubSize + 1, preEnd, rootIdx + 1, inEnd);
        return treeNode;
    }

    /*
    delete specified key from the tree
    Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
    1.Search for a node to remove.
    2.If the node is found, delete the node.

    ref
    todo by own
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode min = findMin(root.right);
            root.val = min.val;
            return deleteNode(root.right, min.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /*
    You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
    top-down
     */
    // effective recursive recursion
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        TreeNode newN = new TreeNode(val);
        if (root == null) {
            return newN;
        }

        TreeNode target = searchParent(root, val);
        if (target == null) {
            return root;
        }
        if (val > target.val) {
            target.right = newN;
        } else {
            target.left = newN;
        }
        return root;
    }

    // 一直找到Leaf
    private TreeNode searchParent(TreeNode root, int newVal) {
        if (root == null) {
            return null;
        }

        if (newVal > root.val) {
            if (root.right != null) {
                return searchParent(root.right, newVal);
            } else {
                return root;
            }
        } else {
            if (root.left != null) {
                return searchParent(root.left, newVal);
            } else {
                return root;
            }
        }
    }

    /*
    You are given the root of a binary search tree (BST) and an integer val.
    Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
    top-down model
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        int curr = root.val;
        if (curr == val) {
            return root;
        }
        // approach 1
        else if (curr > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }

        // approach 2
//        TreeNode l = searchBST(root.left, val);
//        TreeNode r = searchBST(root.right, val);
//        return l != null ? l : r;
    }

    /*
    top-down
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode curr, Integer low, Integer high) {
        if (curr == null) {
            return true;
        }
        int val = curr.val;
        if (low != null && val <= low) {
            return false;
        }
        if (high != null && val >= high) {
            return false;
        }

        boolean leftCheck = isValidBST(curr.left, low, curr.val);
        boolean rightCheck = isValidBST(curr.right, curr.val, high);

        return leftCheck && rightCheck;
    }

    public static void main(String[] args) {
        BST t = new BST();
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {5, 14, 10, 77, 95};

        TreeNode treeNode = t.arr2BST(a2);

        TreeNode treeNode1 = t.insertIntoBST(treeNode, 4);


        System.out.println();
    }
}
