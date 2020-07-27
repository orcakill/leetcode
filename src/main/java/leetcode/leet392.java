package leetcode;

import java.util.HashMap;
import java.util.Map;

public class leet392 {
    public static void main(String[] args) {
        String s = "acb";
        String t = "ahbgdc";
        Solution c = new Solution();
        System.out.println(c.isSubsequence(s, t));
    }


    static class Solution {
        public boolean isSubsequence(String s, String t) {
            int num=0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                String s1 = String.valueOf(s.charAt(i));

                if (t.contains(s1) == false) {
                    return false;

                }
                for (int j = 0; j < t.length(); j++) {

                    String t1 = String.valueOf(t.charAt(j));

                    if(s1.equals(t1)==true) {
                        map.put(j, num);
                        num = num + 1;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int tt1=map.get(i);
                System.out.println(tt1);
                int tt2=map.get(i+1);

                if(tt1>tt2){
                    return  false;
                }

            }


            return true;
        }
    }
}









