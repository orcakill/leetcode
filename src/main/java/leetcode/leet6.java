package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @date 2021/1/21  13:30
 **/
public class leet6 {
    public static void main(String[] args) throws Exception {
        String  str="PAYPALISHIRING";
        System.out.println(convert(str,3));
    }


    public static String convert(String s, int numRows) {
       if(numRows==1){
           return s;
       }
       List<StringBuilder>  list=new ArrayList<>();
       for(int i=0;i<Math.min(s.length(),numRows);i++){
           list.add(new StringBuilder());
       }
       int cur=0;
       boolean goDown=false;
       for(char str:s.toCharArray()){
           list.get(cur).append(str);
           if(cur==0||numRows-cur==1){
               goDown=!goDown;
           }
           cur+=goDown?1:-1;
       }
       StringBuilder s1 = new StringBuilder();
       for(StringBuilder ss:list){
           s1.append(ss);
       }
       return  s1.toString();
    }
}
