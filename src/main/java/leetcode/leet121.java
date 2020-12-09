package leetcode;

import leetclass.TreeNode;

public class leet121 {
    public static void main(String[] args) {
        int a[] = {7, 1, 5, 3, 6, 4};
        int b[] = {7, 6, 5, 4, 3, 2};
        int c[] = {1, 4, 2};
        int d[] = {2, 4, 1};
      System.out.println(maxProfit(a));
       System.out.println(maxProfit(b));
       System.out.println(maxProfit(c));
        System.out.println(maxProfit(d));
    }

    public static int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int a = 0;
        int length=prices.length;
        for(int i=0;i<length;i++){
            for(int j=i;j<length;j++){
                int b=prices[j]-prices[i];
                if(b>a){
                    a=b;
                }
            }
        }
        return   a;
    }
}
