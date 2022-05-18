package leetcode;

import leetclass.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author orcakill
 * @date 2021/4/27  11:37
 **/
public class leet160 {
    public static void main(String[] args) {
        int[] a = {4, 1, 8, 4, 5};
        int[] b = {5, 0, 1, 8, 4, 5};

        ListNode listNode1 = ListNode.makeNode(a);
        ListNode listNode2 = ListNode.makeNode(b);

        System.out.println(ListNode.traverse(getIntersectionNode(listNode1,listNode2)));
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> hashSet = new HashSet<>();

        ListNode curNode = headA;
        while (curNode != null) {
            hashSet.add(curNode);
            curNode = curNode.next;
        }

        curNode = headB;
        while (curNode != null) {
            if(hashSet.contains(curNode)){
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;



    }
}

