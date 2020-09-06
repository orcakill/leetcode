package leetclass;

import java.util.ArrayList;

public class ListNode {
      public int val;
     public ListNode next;
     public ListNode(int x) { val = x; }

    //创建单链表
    public static ListNode makeNode(int[] nums) {
        if (nums.length == 0) return null;
        ListNode listNode = new ListNode(nums[0]);
        ListNode head = listNode;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            listNode.next = node;
            listNode = node;
        }
        return head;
    }

    //遍历链表
    public static ArrayList<Integer> traverse(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        return arrayList;
    }
}
