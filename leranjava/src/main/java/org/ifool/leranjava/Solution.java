package org.ifool.leranjava;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class Solution {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = null;
		
		head = removeNthFromEnd(head, 2);
		while(head != null) {
			System.out.println(head.val);
			head=head.next;
		}
	}
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = head;
        int count = 0;
        while(second != null && count < n+1) {
            second = second.next;
            count++;
        }
        if(count < n+1) { 
            return head.next;
        }
        while(second != null) {
            first = first.next;
            second = second.next;
        }
        ListNode tmp = first.next;
        first.next = tmp.next;
        return head;
    }
}