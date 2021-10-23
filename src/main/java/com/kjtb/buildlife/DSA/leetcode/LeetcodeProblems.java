package com.kjtb.buildlife.DSA.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Dr d
 * @Date 2021-07-09
 **/
public class LeetcodeProblems extends Base {
    ThreadLocal<HashMap<Integer, Integer>> local = ThreadLocal.withInitial(() -> new HashMap<>());

    /*
    输入：str = "babadda"
    输出："bab"
    解释："aba" 同样是符合题意的答案。
     */
    public String longestPalindrome2(String str) {
        return null;
    }
    public String longestPalindrome(String str) {
        // 暴力方案
        if (str.length() < 2) {
            return str;
        }
        String longest = "";

        for (int i = 1; i <= str.length(); i++) {
            for (int slow = 0; slow < i; slow++) {
                String t = str.substring(slow, i );
                if (isPalindrome(t) && t.length() > longest.length()) {
                    longest = t;
                }
            }
        }
        return longest;
    }

    boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0, j = len - 1;
             i < len && i != j;
             i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    /*
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
    输入：nums1 = [1,2], nums2 = [3,4]
    输出：2.50000
    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

     */
    public double findMedianSortedArrays(int[] a, int[] b) {
        double ans = 0;
        //merge into one
        int x = a.length;
        int y = b.length;
        int z = x + y;
        int i = 0, j = 0, k = 0;
        int[] c = new int[z];
        while (i < x && j < y) {
            c[k++] = a[i] < b[j] ? a[i++] : b[j++];
        }
        while (i < x) c[k++] = a[i++];
        while (j < y) c[k++] = b[j++];

        //get mid
        if (z % 2 == 0) {
            ans = (c[z / 2] + c[z / 2 - 1]) / 2.0;
        } else {
            ans = c[(z - 1) / 2];
        }

        return ans;
    }

    /*
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
    输入: s = "dvdf" abcb
    输入: s = "abdbvbbdf"
    输出: 3

    滑动窗口
    start,end/ fast,slow

    abcb
     */
    public int lengthOfLongestSubstring2(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int slow = 0;
        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                slow = Math.max(slow, map.get(key)) + 1;
            }
            // put even already exist for update index
            map.put(key, i);
            max = Math.max(max, i - slow + 1);
        }

        return max;
    }

    public int lengthOfLongestSubstring(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        if (str.length() < 2) {
            return 1;
        }
        int s = 0;//start
        int e = 1;//end
        int max = 0;
        char[] chars = str.toCharArray();

        while (e < chars.length) {
            char v = chars[e];
            for (int i = s; i < e; i++) {
                if (chars[i] == v) {
                    s = i + 1;
                    max = Math.max(e - s + 1, max);
                }
            }
            max = Math.max(e - s + 1, max);
            e++;
        }

        return max;
    }

    /*
    sum = x+y+carry
    999
    1
    0001
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = v1 + v2 + carry;
            carry = sum > 9 ? 1 : 0;
            dummy.next = new ListNode(sum % 10);
            dummy = dummy.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry == 1) {
            dummy.next = new ListNode(1);
        }

        return head.next;
    }

    public int[] twoSum(int[] nums, int target) {
        //val,index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            int v2 = target - v;
            if (map.containsKey(v2)) {
                return new int[]{i, map.get(v2)};
            } else {
                map.put(v, i);
            }
        }
        return new int[2];
    }

    public int majorityElement(int[] nums) {
        int len = nums.length;
        int maj = -1;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int v = nums[i];
            if (map.containsKey(v)) {
                map.put(v, map.get(v) + 1);
            } else {
                map.put(v, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                maj = entry.getKey();
                return maj;
            }
        }

        return maj;
    }

    public static void main(String[] args) {
        LeetcodeProblems t = new LeetcodeProblems();

//        String s = "abcabcbb";
        String s = "abcb";
        String s1 = "dvdf";
        String s2 = "au";
        String s3 = "uuu";
//        int i = t.lengthOfLongestSubstring2("abcbc");
        int[] a1 = {1, 2};
        int[] a2 = {3, 4};
//        double medianSortedArrays = t.findMedianSortedArrays(a1, a2);

        boolean palindrome = t.isPalindrome("cbbd");
        String cbbd = t.longestPalindrome("whdqcudjpisufnrtsyupwtnnbsvfptrcgvobbjglmpynebblpigaflpbezjvjgbmofejyjssdhbgghgrhzuplbeptpaecfdanhlylgusptlgobkqnulxvnwuzwauewcplnvcwowmbxxnhsdmgxtvbfgnuqdpxennqglgmspbagvmjcmzmbsuacxlqfxjggrwsnbblnnwisvmpwwhomyjylbtedzrptejjsaiqzprnadkjxeqfdpkddmbzokkegtypxaafodjdwirynzurzkjzrkufsokhcdkajwmqvhcbzcnysrbsfxhfvtodqabvbuosxtonbpmgoemcgkudandrioncjigbyizekiakmrfjvezuzddjxqyevyenuebfwugqelxwpirsoyixowcmtgosuggrkdciehktojageynqkazsqxraimeopcsjxcdtzhlbvtlvzytgblwkmbfwmggrkpioeofkrmfdgfwknrbaimhefpzckrzwdvddhdqujffwvtvfyjlimkljrsnnhudyejcrtrwvtsbkxaplchgbikscfcbhovlepdojmqybzhbiionyjxqsmquehkhzdiawfxunguhqhkxqdiiwsbuhosebxrpcstpklukjcsnnzpbylzaoyrmyjatuovmaqiwfdfwyhugbeehdzeozdrvcvghekusiahfxhlzclhbegdnvkzeoafodnqbtanfwixjzirnoaiqamjgkcapeopbzbgtxsjhqurbpbuduqjziznblrhxbydxsmtjdfeepntijqpkuwmqezkhnkwbvwgnkxmkyhlbfuwaslmjzlhocsgtoujabbexvxweigplmlewumcone");

        System.out.println();
    }
}
