from typing import Optional

from leetclass.ListCode import make_node, node_list, ListNode


class Solution:
    @staticmethod
    def add_two_numbers(l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        pre=ListNode(0)
        cur=pre
        carry=0
        while l1 is not None or l2 is not None:
                if l1 is not None:
                    x=l1.val
                else:
                    x=0
                if l2 is not None:
                    y=l2.val
                else:
                    y=0
                val=int(x+y+carry)
                carry=int(val/10)
                val=int(val%10)
                cur.next=ListNode(val)
                cur=cur.next
                if l1 is not None:
                    l1=l1.next
                if l2 is not None:
                    l2=l2.next
        if carry==1:
          cur.next=ListNode(carry)
        return pre.next



if __name__ == '__main__':
    l1 = [2, 4, 3]
    l2 = [5, 6, 4]
    node_l1 = make_node(l1)
    node_l2 = make_node(l2)
    list_node = Solution.add_two_numbers(node_l1, node_l2)
    print(node_list(list_node))
