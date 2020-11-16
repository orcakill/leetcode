package leetcode;

import java.util.ArrayList;

public class leet70 {
    public static void main(String[] args) {
        int  a=2;
        int  b=3;
        int  c=4;
        int  d=5;
        int  e=6;
        System.out.println(a+" "+climbStairs(a));
        System.out.println(b+" "+climbStairs(b));
        System.out.println(c+" "+climbStairs(c));
        System.out.println(d+" "+climbStairs(d));
        System.out.println(e+" "+climbStairs(e));


    }
    public static int climbStairs(int n) {
        int n1=1;
        int n2=2;
       if(n==1){
           return n1;
       }
       if(n==2){
           return  n2;
       }
       for(int i=3;i<=n;i++){
           int temp=n2;
           n2=n1+n2;
           n1=temp;
       }

        return n2;
    }

}
