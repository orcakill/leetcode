package leetcode;

public class leet344 {
    public static void main(String[] args) {
        char[] a ={'h','e','l','l','o'};
        System.out.println(reverseString(a));
    }

    public static char[] reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
        return  s;
    }


}
