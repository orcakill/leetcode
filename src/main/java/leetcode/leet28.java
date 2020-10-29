package leetcode;

public class leet28 {
    public static void main(String[] args) {
        String haystack0="hello";
        String needle0="ll";
          /*测试用例2*/
        String haystack1="aaa";
        String needle1="a";
        System.out.println(strStr(haystack1,needle1));

    }

    public static int strStr(String haystack, String needle) {
        int length=needle.length();
        if(length==0){
            return 0;
        }
        int t=-1;
        for(int i=0;i<haystack.length()-length+1;i++){
            String h1=haystack.substring(i,i+length);
            if(h1.equals(needle)){
                t=i;
                break;
            }
        }

        return t;
    }
}
