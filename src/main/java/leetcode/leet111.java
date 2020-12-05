package leetcode;

import leetclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class leet111 {
    public static void main(String[] args) {
       Integer root[]= {3,9,20,null,null,15,7};

        System.out.println(minDepth(TreeNode.createTreeNode(root)));
    }
    public static int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null){
            return  minDepth(root.right)+1;
        }
        if(root.right==null){
            return  minDepth(root.left)+1;
        }
        return Math.min(minDepth(root.right)+1,minDepth(root.left)+1);


    }




}
