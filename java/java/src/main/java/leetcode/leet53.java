package leetcode;

import java.util.ArrayList;


public class leet53 {
    public static void main(String[] args) {
        int[] a ={-2,1,-3,4,-1,2,1,-5,4};
        int[] b ={1};
        int[] c ={-2,1};
        int[] d ={-2,-1};/**/
        System.out.println(maxSubArray(c));
    }

    public static int maxSubArray(int[] nums) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        int  max=nums[0];
        if(nums.length==1){
            return nums[0];
        }
        for (int num : nums) {
            arrayList.add(num);
        }
        for(int i=0;i<arrayList.size();i++){
            int  x=arrayList.get(i);
            if(x>max){
                max=x;
            }
            for(int j=i+1;j<arrayList.size();j++){
                x=x+arrayList.get(j);
                if(x>max){
                    max=x;
                }
            }
        }
        return  max;

    }

}
