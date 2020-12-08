package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet119 {
    public static void main(String[] args) {
        System.out.println(getRow(2));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<rowIndex;i++){
            list.add(i+1);
        }
        return  list;
    }
}
