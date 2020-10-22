package leetcode;


import leetclass.ListNode;

import java.util.ArrayList;

public class leet21 {
    public static void main(String[] args) {
        int a[]={1, 2, 4};
        int b[]={1, 3, 4};
        ListNode listNode1=ListNode.makeNode(a);
        ListNode listNode2=ListNode.makeNode(b);
        System.out.println(ListNode.traverse(mergeTwoLists(listNode1,listNode2)));

    }

     public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

         //创建单链表
         public static ListNode makeNode(int[] nums) {
             //如果数组的长度为0，则返回空
             if (nums.length == 0) return null;
             //定义ListNode的对象，为数组第一个值
             ListNode listNode = new ListNode(nums[0]);
             //填充当前的Listcode
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



    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }




}
