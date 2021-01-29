package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class two_array_list {
    public static  List<List<Integer>> array_list_two(int[][] arr1){
        List<List<Integer>>   llist1=new ArrayList<List<Integer>>();
        for (int[] ints : arr1) {
            List<Integer> list1 = new ArrayList<>();
            int arr2[] = ints;
            for (int i : arr2) {
                list1.add(i);
            }
              llist1.add(list1);
        }
        return  llist1;
    }
}
