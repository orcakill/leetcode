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
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
    for(int i=0;i<n;i++){
         nums1[nums1.length-1-i]=nums2[i];
    }
        ArrayList<Integer> arrayList=new ArrayList<>() ;
        for (int j : nums1) {
            arrayList.add(j);
        }
    arrayList.sort(Comparator.naturalOrder());
    for(int i=0;i<arrayList.size();i++){
        nums1[i]=arrayList.get(i);
    }

    return  nums1;
    }


    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


}
