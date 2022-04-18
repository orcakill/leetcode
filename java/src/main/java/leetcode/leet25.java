package leetcode;

import leetclass.ListNode;

import java.util.ArrayList;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName leet25.java
 * @Description K 个一组翻转链表 TODO 简单理解了处理逻辑
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
		//声明一个ListNode类型的hair变量，1个数据为为0的链表节点
		ListNode hair = new ListNode(0);
		//将hair和head进行拼接为一个新的ListNode
		hair.next = head;
		//声明一个新的ListNode变量pre
		ListNode pre = hair;
		//循环处理，head不为空
		while (head != null) {
			//声明一个新的ListNode的变量tail,赋值剩余部分长度
			ListNode tail = pre;
			// 查看剩余部分长度是否大于等于 k
			for (int i = 0; i < k; ++i) {
				tail = tail.next;
				if (tail == null) {
					return hair.next;
				}
			}
			//剩余部分长度大于k
			//声明nex,剩余部分长度后链表
			ListNode nex = tail.next;
			//获取交换后的数组
			ArrayList<Integer> list =ListNode.traverse (head);
			ArrayList<Integer> list1 =ListNode.traverse (tail);
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
		//声明变量prev
		ListNode prev = tail.next;
		//声明变量p
		ListNode p = head;
		//当p不等于tail时，循环处理
		while (prev != tail) {
			ListNode nex = p.next;
			p.next = prev;
			prev = p;
			p = nex;
		}
		return new ListNode[]{tail, head};
	}
	
}
