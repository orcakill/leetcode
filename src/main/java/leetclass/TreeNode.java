package leetclass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode createTreeNode(Integer[] array) {
        TreeNode root = createTreeNode(array, 1);
        return root;
    }

    private static TreeNode createTreeNode(Integer[] array, int index) {
        if (index > array.length) {
            return null;
        }
        Integer value = array[index - 1];
        if (value == null) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = createTreeNode(array, index * 2);
        node.right = createTreeNode(array, index * 2 + 1);
        return node;
    }

    public static Object createArrayList(TreeNode treeNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        return preOrderRe(arrayList, treeNode);
    }

    public static ArrayList<Integer> preOrderRe(ArrayList<Integer> arrayList, TreeNode biTree) {//递归实现
        TreeNode leftTree = biTree.left;
        TreeNode rightTree = biTree.right;
        arrayList.add(biTree.val);

        if (leftTree != null && rightTree != null) {
            preOrderRe(arrayList, leftTree);
        }
        if (leftTree == null && rightTree != null) {
            arrayList.add(null);
            preOrderRe(arrayList, rightTree);
        }

        if (rightTree != null && leftTree != null) {
            preOrderRe(arrayList, rightTree);
        }
        if (rightTree == null && leftTree != null) {
            arrayList.add(null);
            preOrderRe(arrayList, leftTree);
        }
        return arrayList;


    }
}
