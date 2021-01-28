package leetcode;

public class leet125 {
    public static void main(String[] args) {
        String a="A man, a plan, a canal: Panama";
        String b="`l;`` 1o1 ??;l`";
//        System.out.println(isPalindrome(a));
        System.out.println(isPalindrome(b));

    }
    public static boolean isPalindrome(String s) {
        if(s.length()==0){
            return true;
        }
        String s0=s.replaceAll("[\\pP\\p{Punct}]","");
        String s1= "";
        for(int i=0;i<s0.length();i++){
            String ss=s0.substring(i,i+1).toLowerCase();
            if(!ss.equals(" ")&&!ss.equals("''")){
               s1+=ss;
            }
        }
        StringBuilder s2=new StringBuilder(s1);
        StringBuilder s3=s2.reverse();
        String       s4=new String(s3);

        return s1.equals(s4);
    }
}
