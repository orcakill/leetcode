package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class leet35 {
    public static void main(String[] args) {
        int a[]={1,3,5,6};
        System.out.println(searchInsert(a,5));/*2  */
        int b[]={1,3,5,6};
        System.out.println(searchInsert(b,2));/*1*/
        int c[]={1,3,5,6};
        System.out.println(searchInsert(c,7));/*4*/
        int d[]={1,3,5,6};
        System.out.println(searchInsert(d,0));/*0*/

    }

    public  static int searchInsert(int[] nums, int target) {
        int  no=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<target){
                no=i+1;
            }
        }
        return no;
    }
}
