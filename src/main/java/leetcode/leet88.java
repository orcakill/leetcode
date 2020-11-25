package leetcode;
import  util.array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static util.array_list.array_list_one;

public class leet88 {
    public static void main(String[] args) {
        int  a[]={1,2,3,0,0,0};
        int  b[]={2,5,6};
        int  m=3;
        int  n=3;

        System.out.println(array_list_one(merge(a,m,b,n)).toString());

    }
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
    for(int i=0;i<n;i++){
         nums1[nums1.length-1-i]=nums2[i];
    }
        ArrayList<Integer> arrayList=new ArrayList<>() ;
    for(int i=0;i<nums1.length;i++){
        arrayList.add(nums1[i]);
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
