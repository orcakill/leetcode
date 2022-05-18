package leetcode;

import java.util.ArrayList;

public class leet557 {
    public static void main(String[] args) {
        String  s="Let's take LeetCode contest";
      //  String  s="'s ta";
        System.out.println(reverseWords(s));
    }

    private static String reverseWords(String s) {
        if(s.isEmpty()){
            return s;
        }
        ArrayList<Integer> space=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            String s1=s.substring(i,i+1);
            if(s1.equals(" ")){
                space.add(i);
            }
            if(i==s.length()-1){
                space.add(i+1);
            }
        }
        StringBuilder s0= new StringBuilder();
        int    i1=0;
        for(int i=0;i<space.size();i++){
            String sa=s.substring(i1,space.get(i));
            i1=space.get(i);
            StringBuilder sb=new StringBuilder(sa);
            StringBuilder sc=sb.reverse();
            String  sd=new String(sc);
            if(i==0){
                s0.append(sd).append(" ");
            }
            else {
                s0.append(sd);
            }
        }
        s0 = new StringBuilder(s0.substring(0, s0.length() - 1));

        return s0.toString();
    }
}
