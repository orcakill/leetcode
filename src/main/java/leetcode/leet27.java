package leetcode;

import java.util.ArrayList;

public class leet27 {
    public static void main(String[] args) {
        int a[]={3, 2, 2,3};
        System.out.println(removeElement(a,3));

    }

    public static int removeElement(int[] nums, int val) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
           if(nums[i]!=val){
               a.add(nums[i]);
           }
        }
        for(int i=0;i<a.size();i++){
            nums[i]=a.get(i);
        }

        return a.size();
    }
}
