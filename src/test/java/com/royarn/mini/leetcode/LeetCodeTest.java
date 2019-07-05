package com.royarn.mini.leetcode;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-04
 */
public class LeetCodeTest {
    public static void main(String[] args) {
        //两数之和
        int[] nums = {2, 7, 11, 15};
        //print(Easy.twoSum2(nums, 26));
        //反转字符串
        char[] s = {'a', 'b', 'c', 'd', 'e', 'f'};
        //print(Easy.reverString(s));
        //反转链表
        Medium.ListNode node = new Medium.ListNode(1);
        node.next = new Medium.ListNode(2);
        node.next.next = new Medium.ListNode(3);
        node.next.next.next = new Medium.ListNode(4);
        node.next.next.next.next = new Medium.ListNode(5);
        print(Medium.reverseBetween(node, 2, 4));
    }

    public static void print(int[] nums) {
        System.out.print("[");
        for (int i =0;i<nums.length;i++) {
            if (i < nums.length-1) {
                System.out.print(nums[i] + ", ");
            } else
                System.out.print(nums[i]);
        }
        System.out.print("]");
    }

    public static void print(char[] nums) {
        System.out.print("[");
        for (int i =0;i<nums.length;i++) {
            if (i < nums.length-1) {
                System.out.print(nums[i] + ", ");
            } else
                System.out.print(nums[i]);
        }
        System.out.print("]");
    }

    public static void print(Medium.ListNode node) {
        Medium.ListNode temp = node;
        System.out.print("[");
        for (;;) {
            if (temp == null) {
                break;
            } else {
                System.out.print(temp.val + " ,");
                temp = temp.next;
            }
        }
        System.out.print("]");
    }
}