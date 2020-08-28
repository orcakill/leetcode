package leetcode;

public class leet657 {
    public static void main(String[] args) {
        String s = "LL";
        System.out.println(judgeCircle(s));
    }

    public static boolean judgeCircle(String moves) {
        int i1=0;
        int i2=0;
        for(int i=0;i<moves.length();i++){
            String s1=moves.substring(i,i+1);
            if(s1.equals("U")){
                i1=i1+1;
            }
            if(s1.equals("D")){
                i1=i1-1;
            }
            if(s1.equals("R")){
                i2=i2+1;
            }
            if(s1.equals("L")){
                i2=i2-1;
            }
        }
      if(i1==0&&i2==0){
          return true;
      }

        return false;
    }
}
