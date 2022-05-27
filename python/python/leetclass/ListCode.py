from typing import List


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# 创建单链表
def make_node(nums: List[int]) -> ListNode | None:
    if len(nums) == 0:
        return None
    listNode = ListNode(nums[0])
    head = listNode
    for i in range(1, len(nums)):
        node = ListNode(nums[i])
        listNode.next = node
        listNode = node
    return head


def node_list(head: ListNode) -> list[int]:
    arrayList = []
    while head is not None:
        arrayList.append(head.val)
        head = head.next
    return arrayList
