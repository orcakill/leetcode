package leetcode;

import leetclass.TreeNode;

public class leet112 {
    public static void main(String[] args) {
        Integer  a[]={5,4,8,11,null,13,4,7,2,null,null,null,null,null,1};
        Integer  b[]={};

//        TreeNode treeNode1=TreeNode.createTreeNode(a);
//        System.out.println(hasPathSum(treeNode1,22));

        TreeNode treeNode2=TreeNode.createTreeNode(b);
        System.out.println(hasPathSum(treeNode2,22));
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
      TreeNode left=root.left;
      TreeNode right=root.right;
      if(left==null&&right==null){
          if(sum-root.val==0){
              return true;
          }
          else{
              return false;
          }
      }

      return  hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);

    }
}
