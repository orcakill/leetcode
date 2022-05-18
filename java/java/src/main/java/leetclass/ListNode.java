package leetclass;

import java.util.ArrayList;

public class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode () {
	}
	
	public ListNode (int x) {
		val = x;
	}
	public  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	
	//创建单链表
	public static ListNode makeNode (int[] nums) {
		//如果数组的长度为0，则返回空
        if (nums.length == 0) {
            return null;
        }
		//定义ListNode的对象，为数组第一个值
		ListNode listNode = new ListNode (nums[0]);
		//填充当前的Listcode
		ListNode head = listNode;
		for (int i = 1; i < nums.length; i++) {
			ListNode node = new ListNode (nums[i]);
			listNode.next = node;
			listNode = node;
		}
		return head;
	}
	
	//创建单链表
	public static ListNode[] makeNodeArray (int[][] nums1) {
		ListNode[] listNodes= new ListNode[nums1.length];
		for(int j=0;j<nums1.length;j++){
			int[] nums=nums1[j];
			//如果数组的长度为0，则返回空
			if (nums.length == 0) {
				return null;
			}
			//定义ListNode的对象，为数组第一个值
			ListNode listNode = new ListNode (nums[0]);
			//填充当前的Listcode
			ListNode head = listNode;
			for (int i = 1; i < nums.length; i++) {
				ListNode node = new ListNode (nums[i]);
				listNode.next = node;
				listNode = node;
			}
			listNodes[j]=head;
		}

		return listNodes;
	}
	
	//遍历链表
	public static ArrayList<Integer> traverse (ListNode head) {
		ArrayList<Integer> arrayList = new ArrayList<> ();
		while (head != null) {
			arrayList.add (head.val);
			head = head.next;
		}
		return arrayList;
	}
}


