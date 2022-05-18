package leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class leet4 {
    public static void main(String[] args) {
        int[] num1 ={1,2};
        int[] num2 ={3,4};
        System.out.println(findMedianSortedArrays(num1,num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length==0&&nums2.length==0){
            return 0;
        }
        double num;
        ArrayList arrayList1=new ArrayList();
        ArrayList<Integer>  arrayList2;
        arrayList2 = new ArrayList<>();
        for (int j : nums1) {
            arrayList1.add(j);
        }
        for (int j : nums2) {
            arrayList2.add(j);
        }
        arrayList1.addAll(arrayList2);
        Collections.sort(arrayList1);
        int size=arrayList1.size();
        if(size%2==0){
            int x=size/2;
            int y=x+1;
            num=((double) arrayList1.get(x-1)+(double)arrayList1.get(y-1))/2;
        }
        else{
            int x=(size+1)/2;
            num=(double) arrayList1.get(x-1);
        }

        return num;
    }
}
