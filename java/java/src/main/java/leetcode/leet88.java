package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;



public class leet88 {
    public static void main(String[] args) {
        int[] a ={1,2,3,0,0,0};
        int[] b ={2,5,6};
        int  m=3;
        int  n=3;


    }



    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


}
