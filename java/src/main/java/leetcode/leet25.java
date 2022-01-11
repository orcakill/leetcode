package leetcode;

import leetclass.ListNode;

import java.util.ArrayList;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName leet25.java
 * @Description TODO
 * @createTime 2022年01月11日 10:56:00
 */
public class leet25 {
	public static void main(String[] args) {
		int[] a;
		a = new int[]{1,2,3,4,5};
		int k=2;
		//转换为listNode
		ListNode listNode=ListNode.makeNode (a);
		//两两交换链表中的节点
		ListNode listNode1=reverseKGroup(listNode,k);
		//转换为list
		ArrayList<Integer> list =ListNode.traverse (listNode1);
		//输出
		System.out.println (list);
	}
	
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode hair = new ListNode(0);
		hair.next = head;
		ListNode pre = hair;
		
		while (head != null) {
			ListNode tail = pre;
			// 查看剩余部分长度是否大于等于 k
			for (int i = 0; i < k; ++i) {
				tail = tail.next;
				if (tail == null) {
					return hair.next;
				}
			}
			ListNode nex = tail.next;
			ListNode[] reverse = myReverse(head, tail);
			head = reverse[0];
			tail = reverse[1];
			// 把子链表重新接回原链表
			pre.next = head;
			tail.next = nex;
			pre = tail;
			head = tail.next;
		}
		
		return hair.next;
	}
	
	public static ListNode[] myReverse(ListNode head, ListNode tail) {
		ListNode prev = tail.next;
		ListNode p = head;
		while (prev != tail) {
			ListNode nex = p.next;
			p.next = prev;
			prev = p;
			p = nex;
		}
		return new ListNode[]{tail, head};
	}
	
}
