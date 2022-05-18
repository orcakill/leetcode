package leetcode;

public class leet58 {
    public static void main(String[] args) {
        String s1 = "Hello world";
        String s2 = "a ";
        String s3 ="b   a    ";
        System.out.println(lengthOfLastWord(s3));
    }

    public static int lengthOfLastWord(String s) {
        StringBuilder s1 = new StringBuilder(s);
        StringBuilder s2 = s1.reverse();
        String s3 = new String(s2);
        int length = -1;
        String s4 = s3.trim();
        for (int i = 0; i < s4.length(); i++) {
            String s41 = s4.substring(i, i + 1);
            if (s41.equals(" ")) {
                length = i;
                break;
            }
        }
        if (length == -1) {
            length = s4.length();
        }
        return length;
    }

}
