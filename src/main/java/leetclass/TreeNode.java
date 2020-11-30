package leetclass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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
