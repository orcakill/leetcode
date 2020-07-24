package leetcode;

import static leetcode.leet1025.Solution.divisorGame;

public class leet1025 {
    public static void main(String[] args) {/*leet1025*/
        System.out.println(divisorGame(1));
    }


    static class Solution {
        public static boolean divisorGame(int N) {
            boolean als = false;
            for (int i = 1; i < N; i++) {
                int n=N;
                for (int j=1;j<n;j++){
                if (N % i == 0) {
                    n = N - i;
                }
                    if (i % 2 != 0 && N - i == 1) {
                        als = true;
                        break;
                    } else if (i % 2 == 0 && N - i == 1) {
                        als = false;
                        break;
                    }
                }
            }

            return als;
        }
    }
}
