package leetcode;

public class leetcoe343 {
    public static void main(String[] args) {
        Solution c = new Solution();
        /*   System.out.println(1);*/
        int n=2;
        System.out.println(c.integerBreak(58));

    }

    static class Solution {
        public int integerBreak(int n) {

            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                int curMax = 0;
                for (int j = 1; j < i; j++) {
                    curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
                }
                dp[i] = curMax;
            }
            return dp[n];
        }


    }


}
