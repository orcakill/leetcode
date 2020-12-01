package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class leet104 {
    public static void main(String[] args) {
        Integer a[] = {1, 2, 3};
        Integer b[] = {1, 2, 2, 3, 4, 4, 4};
        Integer c[] = {3,9,20,null,null,15,7};
        Integer d[] = {1, null, 2};
        Integer e[] = {1,2,null,3,null,4,null,5};
          System.out.println(maxDepth(TreeNode.arrayToTreeNode(e))); //5
          System.out.println(maxDepth(TreeNode.arrayToTreeNode(d))); //2
        System.out.println(maxDepth(TreeNode.arrayToTreeNode(a))); //2
        System.out.println(maxDepth(TreeNode.arrayToTreeNode(b))); //3
        System.out.println(maxDepth(TreeNode.arrayToTreeNode(c))); //3
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

        public static TreeNode arrayToTreeNode(Integer[] array){
            if(array.length == 0){
                return null;
            }
            TreeNode root = new TreeNode(array[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean isLeft = true;
            for(int i = 1; i < array.length; i++){
                TreeNode node = queue.peek();
                if(isLeft){
                    if(array[i] != null){
                        node.left = new TreeNode(array[i]);
                        queue.offer(node.left);
                    }
                    isLeft = false;
                }else {
                    if(array[i] != null){
                        node.right = new TreeNode(array[i]);
                        queue.offer(node.right);
                    }
                    queue.poll();
                    isLeft = true;
                }
            }
            return root;
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

    public static int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int n=1;

        return  depth(n,root.left,root.right);
    }

    public static int depth(int n,TreeNode treeNode1,TreeNode treeNode2){
        if(treeNode1==null&&treeNode2==null){
            return n;
        }
        n=n+1;
        if(treeNode1==null){
            return depth(n,treeNode2.left,treeNode2.right);
        }
        if(treeNode2==null){
            return   depth(n,treeNode1.left,treeNode1.right);
        }

        int a= depth(n,treeNode1.left,treeNode1.right);
        int b= depth(n,treeNode2.left,treeNode2.right);
       return Math.max(a, b);
    }


}
