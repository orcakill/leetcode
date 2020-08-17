package leetcode;

import static leetcode.leet1025.Solution.divisorGame;

public class leet64 {
    public static void main(String[] args) {/*leet1025*/
        int A[][]={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(Solution.minPathSum(A));
    }


    static class Solution {

        public static void main(String[] args) {
            int a[][]={{1,3,1},{1,5,1},{4,2,1}};
            System.out.println(minPathSum(a));
        }
                public static int minPathSum(int[][] grid) {
                    if (grid == null || grid.length == 0 || grid[0].length == 0) {
                        return 0;
                    }
                    int rows = grid.length, columns = grid[0].length;
                    int[][] dp = new int[rows][columns];
                    dp[0][0] = grid[0][0];
                    for (int i = 1; i < rows; i++) {
                        dp[i][0] = dp[i - 1][0] + grid[i][0];
                    }
                    for (int j = 1; j < columns; j++) {
                        dp[0][j] = dp[0][j - 1] + grid[0][j];
                    }
                    for (int i = 1; i < rows; i++) {
                        for (int j = 1; j < columns; j++) {
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                        }
                    }
                    return dp[rows - 1][columns - 1];
                }
            }
        }



