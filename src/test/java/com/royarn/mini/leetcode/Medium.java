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


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
}