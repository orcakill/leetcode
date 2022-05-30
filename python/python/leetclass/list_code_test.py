from typing import Optional

from leetclass.list_code import make_node, ListNode, node_list


class Solution:
    @staticmethod
    def test() -> None:
        # 创建首节点
        node_sta = ListNode(0)
        # 声明一个变量用来在移动过程中指向当前节点
        next_node = node_sta
        # 创建链表
        for i in range(1, 10):
            # 生成新的节点
            node = ListNode(i)
            # 把新的节点连起来
            next_node.next = node
            # 把当前节点往后移动,也就是说next后的对象下的next为null
            next_node = next_node.next
        # 重新赋值让它指向首节点
        next_node=node_sta
        # 输出
        print(node_list(node_sta))

        # 替换节点
        while next_node is not None:
            if next_node.val == 4:
                new_node = ListNode(99)
                new_node.next = next_node.next.next
                next_node.next = new_node
            next_node = next_node.next
        # 重新赋值让它指向首节点
        next_node=node_sta
        # 输出
        print(node_list(node_sta))
        # 插入节点
        while next_node is not None:
            if next_node.val == 6:
                new_node = ListNode(99)
                node=next_node.next
                new_node.next = node
                next_node.next = new_node
            next_node = next_node.next
        # 重新赋值让它指向首节点
        next_node=node_sta
        # 输出
        print(node_list(node_sta))



if __name__ == '__main__':
    Solution.test()
