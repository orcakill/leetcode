package leetcode;


import java.util.ArrayList;
import java.util.List;

public class leet118 {
    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> arrayList1 = new ArrayList<>();
        if (numRows < 1) {
            return lists;
        }
        if (1 <= numRows) {
            arrayList1.add(1);
            lists.add(arrayList1);
        }

        for (int k = 0; k < numRows - 1; k++) {
            List<Integer> arrayList2 = new ArrayList<>();
            arrayList2.add(1);
            for (int i = 0; i <= k; i++) {
                int nums = 0;
                for (int j = 0; j <= k; j++) {
                    List<Integer> arrayList3 = lists.get(j);
                    if (arrayList3.size() > i) {
                        nums = nums + arrayList3.get(i);
                    }
                }
                arrayList2.add(nums);
            }
            if (arrayList2.size() > 0) {
                lists.add(arrayList2);
            }

        }

        return lists;
    }
}
