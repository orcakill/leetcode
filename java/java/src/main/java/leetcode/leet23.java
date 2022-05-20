package leetcode;

import leetclass.ListNode;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName leet23.java
 * @Description leet23
 * @createTime 2021年11月22日 08:22:00
 */
public class leet23 {
	public static void main(String[] args) {
		int[][] lists = {{1,4,5},{1,3,4},{2,6}};
		System.out.println (ListNode.traverse (mergeKLists(ListNode.makeNodeArray (lists))));
	}
	
	public static ListNode mergeKLists (ListNode[] lists) {
	    ListNode ans=null;
		for(int i=0;i<lists.length;i++){
		    ans=mergeTwoLists (ans,lists[i]);
		}
		return  ans;
	}
	
	public  static  ListNode mergeTwoLists(ListNode a, ListNode b) {
		if(a==null||b==null){
			return  a!=null?a:b;
		}
		ListNode head=new ListNode (0);
		ListNode tail=head,aPtr=a,bPtr=b;
		while(aPtr!=null&&bPtr!=null){
			if(aPtr.val<bPtr.val){
				tail.next=aPtr;
				aPtr=aPtr.next;
			}
			else{
				tail.next=bPtr;
				bPtr=bPtr.next;
			}
			tail=tail.next;
		}
		tail.next=(aPtr!=null?aPtr:bPtr);
		return  head.next;
	}
}