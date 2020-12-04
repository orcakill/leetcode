package leetclass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

     public TreeNode() {
    }

    public TreeNode(int val) {
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


        public static TreeNode createTreeNode(Integer[] array){
            TreeNode root = createTreeNode1(array, 1);
            return root;
        }

        private static TreeNode createTreeNode1(Integer[] array, int index) {
            if(index > array.length){
                return null;
            }
            Integer value = array[index - 1];
            if(value == null){
                return null;
            }
            TreeNode node = new TreeNode(value);
            node.left = createTreeNode1(array, index * 2);
            node.right = createTreeNode1(array, index * 2 + 1);
            return node;
        }




    public static ArrayList<Integer> createArrayList(TreeNode treeNode) {

        return preOrderRe(treeNode);
    }

    public static ArrayList<Integer> preOrderRe(TreeNode pTreeRoot) {//递归实现
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(pTreeRoot == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(pTreeRoot);
        while(!queue.isEmpty()){

            TreeNode treeNode = queue.poll();

            if(treeNode==null){
                list.add(null);
            }
            else {
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.left == null&&treeNode.right != null) {
                    queue.offer(null);
                }
                if (treeNode.right != null) {
                      queue.offer(treeNode.right);
                }
                if (treeNode.right == null&&treeNode.left != null) {
                    queue.offer(null);
                }
                list.add(treeNode.val);
            }
        }
        if(list.get(list.size()-1)==null){
            list.remove(list.size()-1);
        }
        return list;




    }
}
