package leetcode;

public class leet201 {
    public static void main(String[] args) {
        int m = 2147483647;
        int n = 2147483647;
        System.out.println(rangeBitwiseAnd(m, n));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        if (m == 0 || n == 0) return m;
        int result = m;
        for (int i = m ; i <= n; i++) {
            result &= i;
            if (result == 0) break;
            if (i == Integer.MAX_VALUE) break;
        }
        return result;
    }
}
