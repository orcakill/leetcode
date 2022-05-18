package leetcode;

public class leet9 {
    public static void main(String[] args) {
        int  s1 = -1;
        System.out.println(isPalindrome(s1));

    }
    public  static boolean isPalindrome(int x) {
        String  s=String.valueOf(x);
        StringBuilder s1=new StringBuilder(s);
        StringBuilder s2=s1.reverse();
        String   s3=new String(s2);
        return s.equals(s3);
    }

}
