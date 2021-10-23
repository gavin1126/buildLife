package com.kjtb.buildlife.DSA.leetcode.array;

import com.kjtb.buildlife.DSA.leetcode.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * see
 * https://leetcode.com/explore/learn/card/fun-with-arrays/
 * <p>
 * 数组复盘：
 * 1 不要小看暴力解法，没有暴力解法，就没有接下来的优化，
 * 每道题至少要有2个解法：暴力解法，优化解法 <= O(N)
 * 2 空间换时间思想·
 * 4 逆向思维：从后向前 赋值思想
 * 3 双指针思想
 * *同方向的，快指针，慢指针
 * *反方向的，头指针，尾指针
 * *固定长度的
 * - 环形数组，双指针
 * <p>
 * 读指针，写指针
 * 5 转化为已经做过的相似类型题目
 * 6 增加字段描述标识，（或开关，阈值来减少if分支）
 * 7 若担心 加减后是否会越界，可这样检查： idx+1 < len， idx-1 > -1 等，就不必担心了
 * <p>
 * 先考虑循环一遍就地解决，一遍不行，就多遍
 * <p>
 * <p>
 * other tips
 * result name: rez, ans,
 *
 * @Author Dr d
 * @Date 2021-06-18
 **/
public class Array101 extends Base {

    /*----------------------conclusion------------------------*/

    /*
    Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

    Input: nums = [4,3,2,7,8,2,3,1]
       standard = [0,1,2,3,4,5,6,7]
    Output: [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] std = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            std[v - 1] = 1;
        }
        for (int i = 0; i < std.length; i++) {
            if (std[i] == 0) {
                list.add(i + 1);
            }
        }

        return list;
    }


    /*
    Given integer array nums, return the third maximum number in this array. If the third maximum does not exist, return the maximum number.
    Input: nums = [3,2,1]
    Output: 1

    3 points
     */
    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE, m2 = Long.MIN_VALUE, m3 = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (v == m1 || v == m2 || v == m3) {
                continue;
            }

            if (v > m1) {
                m3 = m2;
                m2 = m1;
                m1 = v;
            } else if (v > m2) {
                m3 = m2;
                m2 = v;
            } else if (v > m3) {
                m3 = v;
            }
        }

        if (m3 == Long.MIN_VALUE) {
            return (int) m1;
        }

        return (int) m3;
    }


    /*
    Return the number of indices where heights[i] != expected[i].
    Input: heights = [1,1,4,2,1,3]
    Output: 3
    Explanation:
    heights:  [1,1,4,2,1,3]
    expected: [1,1,1,2,3,4]
    Indices 2, 4, and 5 do not match.
     */
    public int heightChecker(int[] heights) {
        int n = heights.length;

        int[] sorted = Arrays.copyOf(heights, n);
        Arrays.sort(sorted);

        int diffCnt = 0;

        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] != heights[i]) {
                diffCnt++;
            }
        }
        return diffCnt;
    }

    /*----------------------in place operation------------------------*/

    /*
    Given an array nums of non-negative integers, return an array consisting of all the even elements of nums,
    followed by all the odd elements of nums.

    Input: nums = [3,1,2,5,4]
    Output: [2,4,3,1]
     */
    public int[] sortArrayByParity(int[] nums) {
        int evenId = 0;
        int tmp = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                tmp = nums[evenId];
                nums[evenId] = nums[i];
                nums[i] = tmp;

                evenId++;
            }
        }
        return nums;
    }


    /*
    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1; j < n; j++) {
                    nums[j - 1] = nums[j];
                }
                nums[n - 1] = 0;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int n = nums.length;
        //read,write pointer
        int writePointer = 0;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[writePointer++] = nums[i];
            } else {
                cnt++;
            }
        }

        for (int i = 1; i <= cnt; i++) {
            nums[n - i] = 0;
        }

    }


    /*
      Replace Elements with Greatest Element on Right Side
      Given an array arr, replace every element in that array with the greatest element among the elements to its right,
       and replace the last element with -1.
    Input: arr = [17,18,5,4,6,1]
    Output: [18,6,6,6,1,-1]
     */
    public int[] replaceElements(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i + 1];
            for (int j = i + 1; j < arr.length; j++) {
                if (max < arr[j]) {
                    max = arr[j];
                }
            }
            arr[i] = max;
        }
        arr[arr.length - 1] = -1;

        return arr;
    }

    /*
    backward；
     */
    public int[] replaceElements2(int[] arr) {

        int n = arr.length;
        int max = 0;
        int t = arr[n - 1];

        for (int i = n - 1; i > 0; i--) {
            if (max < t) {
                max = t;
            }
            t = arr[i - 1];
            arr[i - 1] = max;
        }
        arr[n - 1] = -1;
        return arr;
    }


    /*----------------------search------------------------*/

    /**
     * Check If N and Its Double Exist
     * https://leetcode.com/explore/learn/card/fun-with-arrays/527/searching-for-items-in-an-array/3250/
     * <p>
     * 相似：
     * 利用hashmap 做2数只和题，寻找 sum-x，现在是寻找2*x
     * <p>
     * force/violent solution & optimistic solution
     * <p>
     * int[] n7 = {10, 2, 5, 3};        int[] n71 = {7,1,14,11};
     *
     * @param arr unsorted
     * @return
     */
    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == 2 * arr[j] || arr[j] == 2 * arr[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    简化问题，2N or N/2 简化为 2N
    [1,2]
    [2,1]
    [1,1]

     */
    public boolean checkIfExist2(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                return true;
            } else {
                map.put(2 * arr[i], null);
                if (arr[i] % 2 == 0) {
                    map.put(arr[i] / 2, null);
                }
            }
        }
        return false;
    }

    /*
    Valid Mountain Array
    Given an array of integers arr, return true if and only if it is a valid mountain array.
    Recall that arr is a mountain array if and only if:
    * arr.length >= 3
    * 先单调递增，后单调递减

    https://leetcode.com/explore/learn/card/fun-with-arrays/527/searching-for-items-in-an-array/3251/

    {0, 3, 2, 1}
    {1 3 2}
    1 2 3
    3 2 1
     */
    public boolean validMountainArray(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return false;
        }
        boolean isIncrease = true;
        int topIdx = 0;

        for (int i = 0, j = 1; j < len; i++, j++) {
            //排除相等
            if (arr[i] == arr[j]) {
                return false;
            }
            if (isIncrease) {
                //increasing
                if (arr[i] > arr[j]) {
                    isIncrease = false;
                    topIdx = i;
                }
            } else {
                //decreasing
                if (arr[i] < arr[j]) {
                    return false;
                }
            }
        }
        // peak can't be the first or last
        if (topIdx == 0 || topIdx == len - 1) {
            return false;
        }

        return true;
    }

    // 简洁，干净，O(1) 与O(3) 是一样的 O(N)
    public boolean validMountainArray2(int[] arr) {
        int i = 0;
        int N = arr.length;
        //increasing
        while (i + 1 < N && arr[i] < arr[i + 1]) {
            i++;
        }
        //already peak,so check peak
        if (i == 0 || i == N - 1) {
            return false;
        }
        //decreasing
        while (i + 1 < N && arr[i] > arr[i + 1]) {
            i++;
        }
        return i == N - 1;
    }

    /*----------------------delete------------------------*/
    /*
    https://leetcode.com/explore/learn/card/fun-with-arrays/526/deleting-items-from-an-array/3247/
    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]

     */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int leftEleCnt = len;

        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                leftEleCnt--;

                for (int j = i + 1; j < len; j++) {
                    nums[j - 1] = nums[j];
                }
                if (nums[i] == val) {
                    i--;
                }

                nums[leftEleCnt] = Integer.MIN_VALUE;
            }
        }

        return leftEleCnt;
    }

    //two points refer answer
    public int removeElement2(int[] nums, int val) {
        int i = 0;//slow-runner
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    /*
    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]

     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }

//        int j=1;
//        int len = nums.length;
//
//        while (j < len) {
//            if (nums[i] != nums[j]) {
//                i++;
//                nums[i] = nums[j];
//            }
//            j++;
//
//        }
        return i + 1;
    }



    /*----------------------insert------------------------*/

    /*
    Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
    Note that elements beyond the length of the original array are not written.
    Do the above modifications to the input array in-place（就地操作）, do not return anything from your function.

    Input: [1,0,2,3,0,4,5,0]
    Output: null
    Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
    --
    fields:
    aux[]
    i,
    j 0的位置，后移操作

    * */
    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                back1SlutFromIdx(i, arr);
                arr[i] = 0;
                i++;
            }
        }
    }

    // 从指定位置开始，向后平移数组
    void back1SlutFromIdx(int idx, int[] arr) {
        for (int i = arr.length - 1; i > idx; i--) {
            arr[i] = arr[i - 1];
        }

//        for (int i = arr.length - 2; i >= idx; i--) {
//            arr[i + 1] = arr[i];
//        }

    }

    /*
    input: [1,0,2,3,0,4,5,0]

    algo:
    get zero cnt, and how many in the end

    summary
    clear source,dest,aux, final result;
    set ele from the end

     */
    public void duplicateZeros2(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
            if (arr[i] == 0) {
                queue.add(0);
            }
            arr[i] = queue.remove();
        }
    }

    // two index
    public void duplicateZeros3(int[] arr) {
        int idx2 = 0;
        int len = arr.length;
        int[] aux = new int[len];
        for (int i = 0; i < len && idx2 < len; i++) {
            //若为0，则2号指针+1
            if (arr[i] == 0) {
//                aux[idx2] = 0;int数组元素默认为0
                idx2++;
            } else {
                aux[idx2] = arr[i];
            }
            idx2++;
        }

        for (int i = 0; i < len; i++) {
            arr[i] = aux[i];
        }

    }

    /**
     * Merge Sorted Array
     * https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3253/
     * <p>
     * num1 i1,num2 i2
     * assign from end to front
     * <p>
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     * <p>
     * 非确定循环 while 很强大，
     * 常用的check条件：数组索引上下界
     *
     * @param nums1 target arr
     * @param m     非0元素个数
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n-- == 0) return;
        m--;
        int z = nums1.length - 1;
        // m,n is idx
        while (n > -1) {
            if (m > -1 && nums1[m] > nums2[n]) {
                nums1[z--] = nums1[m--];
            } else {
                nums1[z--] = nums2[n--];
            }
        }

        /*int i = m - 1;
        int j = n - 1;

        for (int k = m + n - 1; k >= 0 && j >= 0 && i >= 0; k--) {
            nums1[k] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }*/

    }

    /*----------------------introduce------------------------*/

    /*
      Given an integer array nums sorted in non-decreasing order,
      return an array of the squares of each number sorted in non-decreasing order.
      e.g. nums = [-4,-1,0,3,10]

      sorted to sorted
      O(n)

      负数变正数，

      ---
      参考：
      双指针思想
      if(|lo|>|hi|){
       res[idx] = lo*lo ;
       lo++; idx--
      } else ....

      @param nums
      @return
     */
    public int[] sortedSquares(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int id = hi;

        int[] rez = new int[nums.length];

        while (lo <= hi && id >= 0) {
            if (abs(nums[lo]) > abs(nums[hi])) {
                rez[id--] = nums[lo] * nums[lo];
                lo++;
            } else {
                rez[id--] = nums[hi] * nums[hi];
                hi--;
            }
        }

        return rez;
    }

    int abs(int a) {
        return Math.abs(a);
    }

    /**
     * Find Numbers with Even Number of Digits
     * Given an array nums of integers, return how many of them contain an even number of digits.
     *
     * @param nums
     * @return
     */
    public int findNumbers(int[] nums) {
        int evenNumCnt = 0;
        for (int num : nums) {
            int digitCnt = 0;
            while (num != 0) {
                digitCnt++;
                num /= 10;
            }
            if (digitCnt % 2 == 0) {
                evenNumCnt++;
            }
        }
        return evenNumCnt;
    }

    /**
     * 获取一个数的位数
     *
     * @param num
     * @return
     */
    int getDigitsOfNum(int num) {
        int nunCnt = 0;
        if (num == 0) {
            return 1;
        }

        for (int i = num; i > 0; i /= 10) {
            nunCnt++;
        }

        return nunCnt;
    }

    /*----------------------------------------------*/

    public static void main(String[] args) {
        Array101 t = new Array101();

//        int digitsOfNum = t.getDigitsOfNum(12);
//
//        int[] nums = {12, 345, 2, 23, 6, 7896};
//        int numbers = t.findNumbers(nums);
//
//        int[] nums3 = {-7, -3, 2, 3, 11};
//        int[] ints = t.sortedSquares(nums3);
//
//        int[] nums4 = {1, 0, 2, 3, 0, 4, 5, 0};
////        t.back1SlutFromIdx(2, nums4);
////        t.duplicateZeros2(nums4);
//        t.duplicateZeros3(nums4);
//
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums2 = {2, 5, 6};
////        t.merge(nums1, 3, nums2, 3);
//        t.merge2(nums1, 3, nums2, 3);

//        int[] nums5 = {0, 1, 2, 2, 3, 0, 4, 2};
//        int val = 2;
//        int leftCnt2 = t.removeElement2(nums5, val);
//
//        int[] num6 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int removeDuplicates = t.removeDuplicates(num6);

        int[] n7 = {10, 2, 5, 3};
        int[] n71 = {7, 1, 14, 11};
        int[] n72 = {3, 1, 7, 11};
        boolean b = t.checkIfExist(n72);
        boolean b1 = t.checkIfExist2(n7);

        int[] n8 = {0, 3, 2, 1};
        int[] n81 = {0, 3, 2};
        int[] n83 = {3, 2, 1};
        int[] n82 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean b2 = t.validMountainArray(n83);

        int[] n9 = {17, 18, 5, 4, 6, 1};
        int[] ints = t.replaceElements2(n9);

        int[] n10 = {0, 1, 0, 3, 12};
        int[] n11 = {0};
        t.moveZeroes2(n10);

        int[] n12 = {3, 1, 2, 5, 4};
        int[] ints1 = t.sortArrayByParity(n12);

        int[] n13 = {2, 2, 3, 1};
        int[] n132 = {1, 2, -2147483648};
        int i = t.thirdMax(n132);

    }

}
