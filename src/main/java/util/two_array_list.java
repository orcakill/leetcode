package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class two_array_list {
    public static  List<List<Integer>> array_list_two(int[][] arr1){
        List<List<Integer>> llist1=new ArrayList<List<Integer>>();
        for(int i=0;i<arr1.length;i++){
            List<Integer> list1=new ArrayList<>();
            int arr2[]=arr1[i];
            for(int j=0;j<arr2.length;j++){
                list1.add(arr2[j]);
            }
            llist1.add(list1);
        }
        return  llist1;
    }
}
