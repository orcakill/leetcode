package leetcode;

import java.util.ArrayList;



public class leet100 {
    public static void main(String[] args) {
        Integer[] a1 = {1, 2, 3, 4, 5, 6, 7};
        Integer[] a2 = {1, 2, 3, 4, 5, 6, 7};

        Integer[] b1 = {1, 2};
        Integer[] b2 = {1, null, 2};

        Integer[] c1 = {1, 2, 1};
        Integer[] c2 = {1, 1, 2};


        Integer[] d1 = {10, 5, 15};
        Integer[] d2 = {10, 5, null,null,15};

        Integer[] e1 = {0,-5};
        Integer[] e2 = {0,-8};
        System.out.println(isSameTree(TreeNode.createTreeNode(e1), TreeNode.createTreeNode(e2)));  //false
        System.out.println(isSameTree(TreeNode.createTreeNode(a1), TreeNode.createTreeNode(a2)));  //true
        System.out.println(isSameTree(TreeNode.createTreeNode(b1), TreeNode.createTreeNode(b2)));  //false
        System.out.println(isSameTree(TreeNode.createTreeNode(c1), TreeNode.createTreeNode(c2)));  //false
        System.out.println(isSameTree(TreeNode.createTreeNode(d1), TreeNode.createTreeNode(d2))); //false
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

        public Object createArrayList(TreeNode treeNode) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            return preOrderRe(arrayList, treeNode);
        }

        public ArrayList<Integer> preOrderRe(ArrayList<Integer> arrayList, TreeNode biTree) {//递归实现
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

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p == null){
            return false;
        }
        if(q == null){
            return false;
        }

        TreeNode l1 = p.left;
        TreeNode r1 = p.right;

        TreeNode l2 = q.left;
        TreeNode r2 = q.right;

        if (p.val != q.val) {
            return false;
        }
        if (l1 == null && l2 == null&&r1==r2) {
            return true;
        }
        if (l1 == null && l2 != null) {
            return false;
        }
        if (l1 != null && l2 == null) {
            return false;
        }
        if (r1 == null && r2 == null&&l1==l2) {
            return true;
        }
        if (r1 == null && r2 != null) {
            return false;
        }
        if (r1 != null && r2 == null) {
            return false;
        } else {
            Boolean b1 = isSameTree(l1, l2);
            Boolean b2 = isSameTree(r1, r2);

            return b1 && b2;

        }
    }
}
