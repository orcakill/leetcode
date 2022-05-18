package leetcode;

public class leet459 {
    public static void main(String[] args) {
        String s = "babbabbabbabbab";
        System.out.println(repeatedSubstringPattern(s));
    }

    public static boolean repeatedSubstringPattern(String s) {
        String s0=s.substring(0,1);
        StringBuilder s1;
        for(int i=1;i<s.length();i++){
            String s2=s.substring(i,i+1);
            if(s0.equals(s2)){
                String s3=s.substring(0,i);
                s1 = new StringBuilder(s.substring(0, i));
                for(int j=1;j<s.length()/i;j++){
                    s1.append(s3);
                }
                if(s.equals(s1.toString())){
                    return true;
                }
            }
        }
        return false;
    }
}
