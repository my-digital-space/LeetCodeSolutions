package com.leetcode.solutions._1_to_100._2AddToNumbers;

public class Solution1 {

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        // 4 9 9 5 9
        ListNode l1 = new ListNode(4, new ListNode(9, new ListNode(9,
                new ListNode(5, new ListNode(9)))));
        // 1 9 7
        ListNode l2 = new ListNode(1, new ListNode(9, new ListNode(7)));
        ListNode result = solution1.addTwoNumbers(l1, l2);
        System.out.print("Num1:   ");
        printList(l1);
        System.out.println("");
        System.out.print("Num2:   ");
        printList(l2);
        System.out.println("");
        System.out.print("Result: ");
        printList(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode pointer = result;
        int carry = 0; // default carry of 0

        while (l1 != null || l2 != null) {
            int sum = 0 + carry; // initializing sum

            if (l1 != null) { // using number from the 1st list
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) { // using number from the 2nd list
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            sum = sum % 10;
            pointer.next = new ListNode(sum);
            pointer = pointer.next;
        }

        if (carry == 1) pointer.next = new ListNode(1);

        return result.next;
    }

    // Utility method to print the linked list
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
    }

}
