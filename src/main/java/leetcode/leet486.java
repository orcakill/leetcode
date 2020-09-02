package leetcode;

public class leet486 {
    public static void main(String[] args) {
        int a[]= {1, 5, 2};  //flase
        System.out.println(PredictTheWinner(a));
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
        int i1=scoreStart * turn;
        int i2=scoreStart * turn;
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }


}
