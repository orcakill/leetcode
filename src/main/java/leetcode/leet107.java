package leetcode;


import leetclass.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class leet107 {
    public static void main(String[] args) {
        Integer a[]={3,9,20,null,null,15,7};
        Integer b[]={1,2};
        Integer c[]={1,2,3,4,null,null,5};

        TreeNode treeNode= TreeNode.createTreeNode(c);
        List<List<Integer>>  x=levelOrderBottom(treeNode);
        System.out.println(x);
     // System.out.println(levelOrderBottom(TreeNode.createTreeNode(a)));
    }


    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists=new ArrayList<>();
        ArrayList<Integer> arrayList=new ArrayList();
        int index=1;
        if(root==null){
            return  lists;
        }
        arrayList.add(root.val);
        lists.add(arrayList);
        List<List<Integer>> listes=node(index,lists,root);
        List<List<Integer>>  lists1=new ArrayList<>();
        for(int i=0;i<listes.size();i++){
            lists1.add(listes.get(listes.size()-i-1));
        }
        return lists1;
    }
    public static   List<List<Integer>>  node(int index,List<List<Integer>> lists,TreeNode treeNode){
         ArrayList<Integer> arrayList=new ArrayList<>();
         TreeNode l= treeNode.left;
        TreeNode r = treeNode.right;
        if (l == null && r== null) {
             return lists;
        }
        if(l!=null){
            arrayList.add(l.val);
        }
        if(r!=null){
              arrayList.add(r.val);
        }
        if(index>=lists.size()){
            lists.add(arrayList);
        }
        else{
            lists.get(index).addAll(arrayList);
        }
        index=index+1;
        if(l!=null){
            node(index,lists,l);
        }
        if(r!=null) {
            node(index,lists,r);
        }
        return lists;
    }


}
