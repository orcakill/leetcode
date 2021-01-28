package leetcode;



import java.util.ArrayList;

import static leetcode.leet83.ListNode.traverse;

public class leet83 {
    public static void main(String[] args) {
        int  a[]={1,1,2};
        int  b[]={1,1,2,3,3};
        int  c[]={};

        System.out.println(traverse(deleteDuplicates(ListNode.makeNode(a))).toString());
//        System.out.println(traverse(deleteDuplicates(ListNode.makeNode(b))).toString());
//        System.out.println(traverse(deleteDuplicates(ListNode.makeNode(c))).toString());
    }
  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

      //创建单链表
      public  static ListNode makeNode(int[] nums) {
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
      public static  ArrayList<Integer> traverse(ListNode head) {
          ArrayList<Integer> arrayList = new ArrayList<>();
          while (head != null) {
              arrayList.add(head.val);
              head = head.next;
          }
          return arrayList;
      }

  }
    public static ListNode deleteDuplicates(ListNode head) {
        if (head==null){
            return head;
        }
        ArrayList<Integer> arrayList=new ArrayList<>();
        while (head!=null){
            arrayList.add(head.val);
            head=head.next;
        }
        ArrayList<Integer> arrayList1=new ArrayList<>();
        for (Integer integer : arrayList) {
            if (!arrayList1.contains(integer)) {
                arrayList1.add(integer);
            }
        }
        ListNode listNode = new ListNode(arrayList1.get(0));
        //填充当前的Listcode
        ListNode head1 = listNode;
        for (int i = 1; i < arrayList1.size(); i++) {
            ListNode node = new ListNode(arrayList1.get(i));
            listNode.next = node;
            listNode = node;
        }
        return  head1;
    }
}
