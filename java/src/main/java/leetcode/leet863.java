package leetcode;

import leetclass.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class leet863 {
    public static void main(String[] args) {
        Integer[] a1 ={3,5,1,6,2,0,8,null,null,7,4};
        Integer[] a2 ={5};
        System.out.println(distanceK(TreeNode.createTreeNode(a1),TreeNode.createTreeNode(a2),2));
    }



    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, TreeNode> parents = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        // 从 root 出发 DFS，记录每个结点的父结点
        findParents(parents, root);

        // 从 target 出发 DFS，寻找所有深度为 k 的结点
        findAns(ans,parents,target, null, 0, k);

        return ans;
    }

    public static Map<Integer, TreeNode> findParents(Map<Integer, TreeNode> parents,TreeNode node) {


        if (node.left != null) {
            parents.put(node.left.val, node);
            findParents(parents,node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            findParents(parents,node.right);
        }
        return parents;
    }

    public static void findAns(List<Integer> ans,Map<Integer, TreeNode> parents,TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            ans.add(node.val);
            return;
        }
        if (node.left != from) {
            findAns(ans,parents,node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findAns(ans,parents,node.right, node, depth + 1, k);
        }
        if (parents.get(node.val) != from) {
            findAns(ans,parents,parents.get(node.val), node, depth + 1, k);
        }
    }





}
