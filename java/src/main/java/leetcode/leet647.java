package leetcode;

public class leet647 {
    public static void main(String[] args) {
        String s= "aaa";
        System.out.println(countSubstrings(s));
    }

   public static int countSubstrings(String s) {
       /*定义返回变量*/
        int num=0;
        int i1=s.length();
        for(int i=0;i<s.length();i++){
            for(int j=0;j<i1;j++){

                String s1=s.substring(j,j+i+1);
                StringBuilder sb1=new StringBuilder(s1);
                StringBuilder sb2=sb1.reverse();
                String s2=new String(sb2);
                if(s1.equals(s2)){
                    num+=1;
                }

            }
            i1=i1-1;

        }
        return num;
  }

}
