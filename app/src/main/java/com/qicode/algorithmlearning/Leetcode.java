package com.qicode.algorithmlearning;

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


            if(L1ScanNode != null){
                L1ScanNode = L1ScanNode.next;
            }

            if(L2ScanNode != null){
                L2ScanNode = L2ScanNode.next;
            }

            if(L1ScanNode != null || L2ScanNode != null){//创建节点条件
                /**
                 * 下个Node
                 */
                LScanNode.next = new ListNode(0);
                LScanNode = LScanNode.next;//链接下个节点
            }
        }
        //最后的进位处理,如果有进位,追加最高位节点
        if(carry){
            LScanNode.next = new ListNode(1);
        }
        return result;
    }
}
