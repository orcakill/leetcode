package leetcode;


import leetclass.ListNode;

public class leet2 {
    public static void main(String[] args) {
      int  a[]={2,4,3};
      int  b[]={5,6,4};
      //定义a为ListNode
      ListNode l1=ListNode.makeNode(a);
      ListNode l2=ListNode.makeNode(b);

      ListNode  s=addTwoNumbers(l1,l2);
      System.out.println(ListNode.traverse(s));

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;


    }

}
