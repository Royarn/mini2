package com.royarn.mini.leetcode;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-04
 */
public class Medium {

    public static Medium.ListNode reverseBetween(ListNode head, int m, int n) {
        m = m-1;
        n = n-1;
        ListNode front = head;
        for (int i=0;;) {
            if (i == m) {
                Medium.ListNode end = head;
                for (int j=0;;) {
                    if (j == n) {
                        int val = end.val;
                        end.val = front.val;
                        front.val = val;
                        n--;
                        break;
                    }
                    end = end.next;
                    j++;
                }
                m++;
            }
            front = front.next;
            i++;
            if (m >= n) break;
        }
        return head;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode first = l1;
        ListNode second = l2;
        int reduce = 0;
        for (int i=0;;) {
            int num1 = 0;
            int num2 = 0;
            int checkNum = 0;
            if (first == null && second == null) {
                break;
            }
            if (first != null) {
                num1 = first.val;
                first = first.next;
            }
            if (second != null) {
                num2 = second.val;
                second = second.next;
            }
            if (num1 + num2 + reduce >= 10) {
                checkNum = num1 + num2 + reduce -10;
                reduce = 1;
            } else {
                checkNum = num1 + num2 + reduce;
                reduce = 0;
            }
            if (i ==0) {
                result.val = checkNum;
            }
            if (i == 1) {
                result.next = new ListNode(checkNum);
            }
            if (i == 2) {
                result.next.next = new ListNode(checkNum);
            }
           i++;
        }
        return result;
    }

    public static int threeSumClosest(int[] nums, int target) {

        return 0;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        System.out.println(10/3);
    }
}