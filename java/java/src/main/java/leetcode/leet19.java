package leetcode;

import leetclass.ListNode;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName leet19.java
 * @Description 删除链表的倒数第 N 个结点
 * @createTime 2021年10月14日 08:17:00
 */
public class leet19 {
	public static void main (String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int n = 2;
		ListNode listNode = ListNode.makeNode (a);
		System.out.println (ListNode.traverse (removeNthFromEnd (listNode, n)));
	}
	
	public static ListNode removeNthFromEnd (ListNode head, int n) {
		ListNode dummy = new ListNode(0, head);
		int length = getLength(head);
		ListNode cur = dummy;
		for (int i = 1; i < length - n + 1; ++i) {
			cur = cur.next;
		}
		cur.next = cur.next.next;
		ListNode ans = dummy.next;
		return ans;
	}
	
	public static int getLength(ListNode head) {
		int length = 0;
		while (head != null) {
			++length;
			head = head.next;
		}
		return length;
	}
	

}
