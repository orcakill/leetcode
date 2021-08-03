package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet581 {
    public static void main(String[] args) {
        int[] a1 = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(a1));
    }

    public static int findUnsortedSubarray(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] nums1 = new int[nums.length];
        System.arraycopy(nums, 0, nums1, 0, nums.length);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != nums1[i]) {
                list.add(i);
            }
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1) - list.get(0) + 1;
    }
}
