package leetcode;

public class leet9 {
    public static void main(String[] args) {
        int  s1 = -1;
        System.out.println(isPalindrome(s1));

    }
    public  static boolean isPalindrome(int x) {
        String  s=String.valueOf(x);
        StringBuffer s1=new StringBuffer(s);
        StringBuffer  s2=s1.reverse();
        String   s3=new String(s2);
        if(s.equals(s3)){
            return true;
        }
        return  false;
    }

}
