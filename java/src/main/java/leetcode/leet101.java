package leetcode;

import java.util.ArrayList;

public class leet101 {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 2, 3, 4, 4, 3};
        Integer[] b = {1, 2, 2, 3, 4, 4, 4};

        System.out.println(isSymmetric(TreeNode.createTreeNode(a))); //true
        System.out.println(isSymmetric(TreeNode.createTreeNode(b))); //false
    }

    public static class TreeNode {
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
            return createTreeNode(array, 1);
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
            arrayList.add(biTree.val)  ;

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
    public static boolean isSymmetric(TreeNode root) {
        if(root==null){
            return  true;
        }
        return  compare(root.left,root.right);
    }

    public static boolean compare(TreeNode left,TreeNode right) {
        if(left == null)
            return right==null;
        if(right == null)
            return false;
        if(left.val != right.val)
            return false;
        return compare(left.right, right.left) && compare(left.left, right.right);
    }


}
