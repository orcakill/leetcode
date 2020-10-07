package leetcode;

import  util.array_list;

import java.util.Arrays;

public class leet75 {
    public static void main(String[] args) {/*leet1025*/
        int A[]={2,0,2,1,1,0};
        System.out.println(Arrays.toString(sortColors(A)));
    }

    public static int[] sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        return nums;
    }

}
