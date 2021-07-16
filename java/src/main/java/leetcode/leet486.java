package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet486 {
    public static void main(String[] args) {
        int[] a = {1, 5, 2};  //flase
        System.out.println(PredictTheWinner(a));

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(Arrays.toString(list.toArray()));

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(Arrays.toString(list1.toArray()));

    }

    public static boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public static int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }


}
