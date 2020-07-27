package leetcode;

public class leet392 {
    public static void main(String[] args) {
        String s = "acb";
        String t = "ahbgdc";
        Solution c = new Solution();
        System.out.println(c.isSubsequence(s, t));
    }


    static class Solution {
        public boolean isSubsequence(String s, String t) {
            for (int i = 0; i < s.length(); i++) {
                String s1 = String.valueOf(s.charAt(i));


                int j1 = 0;
                int j2 = 0;
                if (t.contains(s1) == false) {
                    return false;

                }
                for (int j = 0; j < t.length(); j++) {
                    String t1 = String.valueOf(t.charAt(j));

                    if (t1.equals(s1)) {
                        System.out.println(t1);
                        j1 = i;
                        System.out.println(j1);
                        System.out.println(j2);
                        if (t.length() > 1) {
                            if (j2 >j1) {
                                return false;
                            }
                            j2=i;
                        }
                        else{
                            return  true;
                        }



                    }


                }

            }

            return true;
        }
    }
}
