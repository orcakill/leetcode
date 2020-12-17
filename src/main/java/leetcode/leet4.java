package leetcode;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;

public class leet4 {
    public static void main(String[] args) {
        int num1[]={1,3};
        int num2[]={2};
        System.out.println(findMedianSortedArrays(num1,num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double num=0;
        ArrayList arrayList1=new ArrayList();
        ArrayList arrayList2=new ArrayList();
        for(int i=0;i<nums1.length;i++){
            arrayList1.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++){
            arrayList2.add(nums2[i]);
        }
        arrayList1.addAll(arrayList2);
        Collections.sort(arrayList1);
        int size=arrayList1.size();


        return 1;
    }
}
