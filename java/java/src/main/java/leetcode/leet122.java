package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet122 {
    public static void main(String[] args) {
        int[] a = {7, 1, 5, 3, 6, 4};
        int[] b = {7, 6, 5, 4, 3, 2};
        int[] c = {1, 4, 2};
        int[] d = {2, 4, 1};
        int[] e = {1, 2, 3,4,5};
        int[] f = {6, 1, 3,2,4,7};
       System.out.println(maxProfit(a));
        System.out.println(maxProfit(b));
        System.out.println(maxProfit(c));
        System.out.println(maxProfit(d));
        System.out.println(maxProfit(e));
        System.out.println(maxProfit(f));
    }

    public static int maxProfit(int[] prices) {
        int sum = 0;
       List<List<Integer>> lists =new ArrayList<>();
        for (int val : prices) {
            if (lists.size() == 0) {
                ArrayList<Integer> list1 = new ArrayList<>();
                list1.add(val);
                lists.add(list1);
            } else {
                List<Integer> list2 = lists.get(lists.size() - 1);
                List<Integer> list3 = new ArrayList<>();
                int val1 = list2.get(list2.size() - 1);
                if (val >= val1) {
                    list2.add(val);
                    lists.set(lists.size() - 1, list2);
                } else {
                    list3.add(val);
                    lists.add(list3);
                }
            }
        }
        for (List<Integer> list4 : lists) {
            if (list4.size() > 0) {
                sum += list4.get(list4.size() - 1) - list4.get(0);
            }
        }



       return sum;

    }
}