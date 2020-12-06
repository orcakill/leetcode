package leetcode;

import leetclass.TreeNode;

import java.util.ArrayList;


public class leet108 {
    public static void main(String[] args) {
        int  a[]={-10,-3,0,5,9};

        System.out.println(TreeNode.createArrayList(sortedArrayToBST(a)));
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode treeNode=helper(nums, 0, nums.length - 1);
        ArrayList<Integer> arrayList=TreeNode.createArrayList(treeNode);
            return helper(nums, 0, nums.length - 1);
        }

        public static TreeNode helper(int[] nums, int left, int right) {
            if (left > right) {
                return  null;
            }

            // 总是选择中间位置右边的数字作为根节点
            int mid = (left + right + 1) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, left, mid - 1);
            root.right = helper(nums, mid + 1, right);
            return root;

        }


}
