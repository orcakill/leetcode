package leetcode;

import java.util.ArrayList;

public class leet26 {
    public static void main(String[] args) {
        int a[]={1, 1, 2};
        System.out.println(removeDuplicates(a));

    }

    public static int removeDuplicates(int[] nums) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!a.contains(nums[i])) {
                a.add(nums[i]);
            }
        }
        for(int i=0;i<a.size();i++){
            nums[i]=a.get(i);
        }

        return a.size();

    }
}
