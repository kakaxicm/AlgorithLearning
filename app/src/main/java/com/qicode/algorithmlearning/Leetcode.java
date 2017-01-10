package com.qicode.algorithmlearning;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenming on 17/1/9.
 * from : https://leetcode.com/problems/
 */

public class Leetcode {
    /**
     * 169. Majority Element
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     * 思路:
     * 两两比较:
     * 如果相同，则继续向前,如果不同,则剔除掉这两个元素
     */

    public static int getMajorityItem(int[] array) {
        int tempCount = 0;//临时计数
        int item = 0;

        for (int i = 0; i < array.length; i++) {
            if (tempCount == 0) {//此时item计数被抵消至0,剔除，判断下一个元素,重新计数
                item = array[i];
                tempCount = 1;
            } else {
                if (item == array[i]) {
                    tempCount++;//继续向前
                } else {
                    tempCount--;//抵消
                }
            }
        }
        return item;
    }

    /**
     * 1.Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution.
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     * 思路:前提条件决定num数组没有重复元素，元素可以作为Hashmap的Key,结合Hashmap, 在map查找元素及target-元素
     */

    public static int[] getSumIndexes(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int param1Key = target - nums[i];
            if (map.containsKey(param1Key)) {
                result[0] = map.get(param1Key);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);

        }

        return result;
    }

    /***
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result;
        ListNode L1ScanNode = l1;
        ListNode L2ScanNode = l2;

        ListNode LScanNode = new ListNode(0);
        result = LScanNode;
        boolean carry = false;//进位

        while (L1ScanNode != null || L2ScanNode != null) {

            int a = L1ScanNode == null ? 0 : L1ScanNode.val;
            int b = L2ScanNode == null ? 0 : L2ScanNode.val;
            int temp = a + b + (carry ? 1 : 0);
            carry = temp >= 10;//进位
            LScanNode.val = carry ? temp % 10 : temp;


            if (L1ScanNode != null) {
                L1ScanNode = L1ScanNode.next;
            }

            if (L2ScanNode != null) {
                L2ScanNode = L2ScanNode.next;
            }

            if (L1ScanNode != null || L2ScanNode != null) {//创建节点条件
                /**
                 * 下个Node
                 */
                LScanNode.next = new ListNode(0);
                LScanNode = LScanNode.next;//链接下个节点
            }
        }
        //最后的进位处理,如果有进位,追加最高位节点
        if (carry) {
            LScanNode.next = new ListNode(1);
        }
        return result;
    }

    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * Examples:
     * Given "abcabcbb", the answer is "abc", which the length is 3.
     * Given "bbbbb", the answer is "b", with the length of 1.
     * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * 思路: 记录扫过的互斥元素及字串首尾指针(left, right):迭代处理如下
     * 1.如果集合中不包含当前元素curItem,right指针继续右移
     * 2.如果集合中包含当前元素curItem,处理包括以下三步
     * 1> 当前得到的最大子串长度和上次最大子串长度比较,如果大于，则更新max_left和max的临时计算结果
     * 2> 下一次遍历的准备工作:left = hittedIndex + 1
     * 3.节点索引加入map，记录所有节点最后的索引
     */

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        //左右指针
        int right;
        int left = 0;
        int max = 0;
        int max_left = 0;
        for (right = 0; right < chars.length; right++) {
            char item = chars[right];
            if (map.containsKey(item)) {
                int preHittedIndex = map.get(item) + 1;
                int curSubStringLen = right - left;
                if (curSubStringLen > max) {//保存位置
                    max_left = left;
                }
                left = Math.max(left, preHittedIndex);
            }
            max = Math.max(max, right - left + 1);
            map.put(item, right);
        }
        return max;
    }

    private static int mMaxSubStrLen;
    private static int mMaxSubStartIndex;

    /**
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     * 最大回文问题,思路:
     * 每次遍历, 以该元素为中心,分奇偶两种情况，向两边遍历,检测出回文长度
     */
    private static void checkPalindromicStr(char[] arr, int centerLeft, int centerRight) {
        int left = centerLeft;
        int right = centerRight;
        int subStrLen = 0;
        while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
            left--;
            right++;
        }
        subStrLen = right - left - 1;
        if (subStrLen > mMaxSubStrLen) {
            mMaxSubStrLen = subStrLen;
            mMaxSubStartIndex = left + 1;
        }
    }

    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            checkPalindromicStr(chars, i, i);//奇数子串查找
            checkPalindromicStr(chars, i, i + 1);//偶数子串查找
        }
        Log.e("TAG", "mMaxSubStartIndex = " + mMaxSubStartIndex + ", mMaxSubStrLen = " + mMaxSubStrLen);
        String result = s.substring(mMaxSubStartIndex, mMaxSubStartIndex + mMaxSubStrLen);
        Log.e("TAG", result);
        return result;
    }

    /**
     * Reverse digits of an integer.
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     * 递归实现
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {

        if (x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
            return 0;
        }

        if (Math.abs(x) < 10) {
            return x;
        }

        int pow = 0;
        int temp = x / 10;
        while (Math.abs(temp) > 0) {
            pow++;
            temp /= 10;
        }
        int result = (int) (reverse(x / 10) + (x % 10) * Math.pow(10, pow));
        if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE) {
            return 0;
        }
        return result;
    }

    /**
     * Determine whether an integer is a palindrome. Do this without extra space.
     * 思路:回文遍历原则:从中间向两边遍历,centerIndex分奇数和偶数两种
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
            return false;
        }
        if(x < 0){//排除负数
            return false;
        }
        int digitNum = digitNumberOf(x);
        int centerIndexLeft;//从中间向两边遍历
        int centerIndexRight;

        if (digitNum % 2 == 1) {
            //奇数
            centerIndexLeft = centerIndexRight = digitNum / 2;
        } else {
            centerIndexLeft = digitNum / 2;//left向左递增
            centerIndexRight = centerIndexLeft - 1;//right向右递减
        }

        while(centerIndexLeft <= digitNum && centerIndexRight >= 0){
            if(getDigitAt(centerIndexLeft, x) == getDigitAt(centerIndexRight, x)){
                centerIndexLeft ++;
                centerIndexRight --;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * x的位数
     * @param x
     * @return
     */
    private static int digitNumberOf(int x) {
        int pow = 0;
        int temp = x;
        while (Math.abs(temp) > 0) {
            pow++;
            temp /= 10;
        }
        return pow;
    }

    /**
     * 从低位到高位，各个位数的数字
     *
     * @param index
     * @return
     */
    private static int getDigitAt(int index, int x) {
        int pos = 0;
        int result = 0;
        while (pos <= index) {
            result = x % 10;
            x = x / 10;
            pos++;
        }
        return result;
    }
}
