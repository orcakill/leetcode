package leetcode;

import java.util.ArrayList;
import java.util.List;
import util.array_list;
import  util.two_array_list;

public class leet841 {
    public static void main(String[] args) {
        int a[][]={{1},{2},{3},{}};
        int b[][]={{1,3},{3,0,1},{2},{0}};
        int  c[]={1,2,3};

        int d[][]={{1,3},{3,0,1},{2},{0}};
        int e[][]={{4},{3},{},{2,5,7},{1},{},{8,9},{},{},{6}};
                 // 0   1   2   3      4   5   6    7  8   9
        int f[][]={{6,7,8},{5,4,9},{},{8},{4},{},{1,9,2,3},{7},{6,5},{2,3,1}};
        List<List<Integer>> list1=two_array_list.array_list_two(e);
        System.out.println(canVisitAllRooms(list1));


    }



    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Integer> list0=new ArrayList<>();
        for(int i=0;i<rooms.size();i++){
            list0.add(0);
        }
        list0.set(0,1);
        return  digui(rooms,list0);
    }

    public static   boolean digui(List<List<Integer>> rooms,List<Integer> list0){
       for(int i=0;i<list0.size();i++) {
              if(1 == list0.get(i)){
                  for (int j = 0; j < rooms.get(i).size(); j++) {
                      list0.set(rooms.get(i).get(j), 1);
                  }
              }

       }
        return true;
    }

}
