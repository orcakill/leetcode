package leetcode;

/**
 * @author orcakill
 * @date 2021/3/29  8:27
 **/
public class leet10 {
    public static void main(String[] args) {
//        String s="a";
//        String p="aa";

        String s="aa";
        String p="a*";

        System.out.println(isMatch(s,p));

    }

    public static boolean isMatch(String s, String p) {
        int  m=s.length();
        int  n=p.length();
        boolean f[][]=new boolean[m+1][n+1];
//      f[0][0]代表s和p均为空字符串，f[1][1]代表s和p的第一个字符（即在s和p中下标为0的字符）
        f[0][0]=true;
        for(int i=0;i<=m;++i){
            for (int j=1;j<=n;++j){
                //p的第j个字符为*
                if(p.charAt(j-1)=='*'){
                    //匹配s的第i个字符和p的第j-1个字符
                      if(matches(s,p,i,j-1)){
                          //p中*前面的字符在s中出现多次或者在s中只出现1次
                          f[i][j]=f[i-1][j]||f[i][j-2];
                      }
                      else{
                           //p中*前面的在s中字符出现0次
                          f[i][j]=f[i][j-2];
                      }
                }
                //p的第j个字符不为*
                else{
                    //匹配s的第i个字符和p的第j个字符
                    if(matches(s,p,i,j)){
                        //匹配成功，状态转移；匹配不成功，默认是false
                        f[i][j]=f[i-1][j-1];
                    }
                }

            }
        }
        return f[m][n];
    }

    private  static boolean matches(String s,String p,int i,int j){
       //注意在字符串中的下标变换
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);

    }
}
