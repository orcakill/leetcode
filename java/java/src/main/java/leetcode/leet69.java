package leetcode;

public class leet69 {
    public static void main(String[] args) {
        int  a=0;
        int  b=2147395600;
        int  c=4;
        int  d=17;
        int  e=8;
        System.out.println(a+" "+mySqrt(a));
        System.out.println(b+" "+mySqrt(b));
        System.out.println(c+" "+mySqrt(c));
        System.out.println(d+" "+mySqrt(d));
        System.out.println(e+" "+mySqrt(e));
    }
    public static int mySqrt(int x) {
        double err = 1e-15;

        double t = x;

        while( Math.abs(t - x/t) > err * t)

            t = (x/t + t) /2.0;

        return (int)t;
    }
}
