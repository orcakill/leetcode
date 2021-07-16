package leetcode;


import java.util.ArrayList;
import java.util.Arrays;

public class leet66 {
    public static void main(String[] args) {
        int[] a ={1,2,3};
        int[] b ={4,3,2,1};
        int[] c ={0};
        int[] d ={9,8,7,6,5,4,3,2,1,0};
        int[] e ={9,9};
        System.out.println(Arrays.toString(plusOne(e)));
    }
     public static int[] plusOne(int[] digits) {
         ArrayList<Integer>  arrayList=new ArrayList<>();
         for(int i=0;i<digits.length;i++){
             arrayList.add(digits[digits.length-1-i]);
         }
         arrayList.set(0,arrayList.get(0)+1);
         for(int i=0;i<arrayList.size();i++){
             if(arrayList.get(i)==10){
                 arrayList.set(i,0);
                if(i==arrayList.size()-1){
                    arrayList.add(1);
                }
                else{
                    arrayList.set(i+1,arrayList.get(i+1)+1);
                }

             }
         }
         int nums[]=new int[arrayList.size()];
         for(int i=0;i<arrayList.size();i++){
             nums[i]=arrayList.get(arrayList.size()-1-i);
         }

         return  nums;
     }
}
