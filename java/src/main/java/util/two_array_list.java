package util;

import java.util.ArrayList;

import java.util.List;

public class two_array_list {
    public static  List<List<Integer>> array_list_two(int[][] arr1){
        List<List<Integer>>   llist1= new ArrayList<>();
        for (int[] ints : arr1) {
            List<Integer> list1 = new ArrayList<>();
            for (int i : ints) {
                list1.add(i);
            }
              llist1.add(list1);
        }
        return  llist1;
    }
}
