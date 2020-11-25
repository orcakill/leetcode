package leetcode;




//完全不懂
public class leet110 {
    public static void main(String[] args) {
        TreeNode t=new TreeNode();
        System.out.println(leet110.Solution.isBalanced(t));
    }

    public static class TreeNode {

        int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }


        public TreeNode() {

        }
    }


    static class Solution {
        public static boolean isBalanced(TreeNode root) {
                return height(root) >= 0;
            }

            public static int height(TreeNode root) {
                if (root == null) {
                    return 0;
                }
                int leftHeight = height(root.left);
                int rightHeight = height(root.right);
                if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                    return -1;
                } else {
                    return Math.max(leftHeight, rightHeight) + 1;
                }
            }

        }



}
