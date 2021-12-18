package leetcode;

import leetclass.ListNode;

import java.util.ArrayList;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName leet24.java
 * @Description 两两交换链表中的节点
 * @createTime 2021年12月17日 11:42:00
 */
public class leet24 {
	public static void main(String[] args) {
		int a[]={1,2,3,4};
	    //转换为listNode
		ListNode listNode=ListNode.makeNode (a);
	    //两两交换链表中的节点
		ListNode listNode1=swapPairs (listNode);
	    //转换为list
		ArrayList<Integer> list =ListNode.traverse (listNode1);
		//输出
		System.out.println (list);
	}
	
	public static ListNode swapPairs(ListNode head) {
		while (head==null||head.next==null){
			return  head;
		}
		ListNode newHead=head.next;
		head.next=swapPairs (newHead.next);
		newHead.next=head;
		return newHead;
	}
}
