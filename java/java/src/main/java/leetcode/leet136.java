package leetcode;

import java.util.ArrayList;

public class leet136 {
    public static void main(String[] args) {
          int[] a ={2,2,1};
        int[] b ={4,1,2,1,2};
        System.out.println(singleNumber(a));
        System.out.println(singleNumber(b));
    }
    public static int singleNumber(int[] nums) {
        ArrayList<Integer> list=new ArrayList<>();
        int x=0;
       for(int i=0;i<nums.length;i++){
           for(int j=i+1;j<nums.length;j++){
               int a=nums[i];
               int b=nums[j];
               if(a==b){
                   list.add(a);
               }
           }
       }
        for (int num : nums) {
            if (!list.contains(num)) {
                x = num;
            }
        }
        return x;
    }
}
