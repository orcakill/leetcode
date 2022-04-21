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
		System.out.println ("hair"+" "+ListNode.traverse (hair));
		System.out.println ("head"+" "+ListNode.traverse (head));
		System.out.println ("pre"+" "+ListNode.traverse (pre));
		int num=1;
		while (head != null) {
			System.out.println ("第"+num+"次处理");
			//声明一个新的ListNode的变量tail,赋值剩余部分长度
			ListNode tail = pre;
			System.out.println ("hair"+" "+ListNode.traverse (hair));
			System.out.println ("head"+" "+ListNode.traverse (head));
			System.out.println ("pre"+" "+ListNode.traverse (pre));
			System.out.println ("tail"+" "+ListNode.traverse (tail));
			// 查看剩余部分长度是否大于等于 k
			for (int i = 0; i < k; ++i) {
				tail = tail.next;
				System.out.println ("tail"+" "+ListNode.traverse (tail));
				if (tail == null) {
					return hair.next;
				}
			}
			//剩余部分长度大于k
			//声明nex,剩余部分长度后链表
			ListNode nex = tail.next;
			System.out.println ("nex"+" "+ListNode.traverse (nex));
			//获取交换后的数组
			System.out.println ("交换前");
			System.out.println ("head"+" "+ListNode.traverse (head));
			System.out.println ("tail"+" "+ListNode.traverse (tail));
			ListNode[] reverse = myReverse(head, tail);
			head = reverse[0];
			tail = reverse[1];
			System.out.println ("交换后");
			System.out.println ("head"+" "+ListNode.traverse (head));
			System.out.println ("tail"+" "+ListNode.traverse (tail));
			// 把子链表重新接回原链表
			pre.next = head;
			System.out.println ("pre"+" "+ListNode.traverse (pre));
			tail.next = nex;
			System.out.println ("tail"+" "+ListNode.traverse (tail));
			pre = tail;
			System.out.println ("pre"+" "+ListNode.traverse (pre));
			head = tail.next;
			System.out.println ("head"+" "+ListNode.traverse (head));
			num++;
		}
		
		return hair.next;
	}
	
	public static ListNode[] myReverse(ListNode head, ListNode tail) {
		//声明变量prev
		ListNode prev = tail.next;
		//声明变量p
		ListNode p = head;
		//当p不等于tail时，循环处理
		System.out.println ("链表节点翻转");
		System.out.println ("head"+" "+ListNode.traverse (head));
		System.out.println ("tail"+" "+ListNode.traverse (tail));
		System.out.println ("prev"+" "+ListNode.traverse (prev));
		System.out.println ("p"+" "+ListNode.traverse (p));
		while (prev != tail) {
			ListNode nex = p.next;
			System.out.println ("nex"+" "+ListNode.traverse (nex));
			p.next = prev;
			System.out.println ("p"+" "+ListNode.traverse (p));
			prev = p;
			System.out.println ("prev"+" "+ListNode.traverse (prev));
			p = nex;
			System.out.println ("p"+" "+ListNode.traverse (p));
		}
		return new ListNode[]{tail, head};
	}
	
}
