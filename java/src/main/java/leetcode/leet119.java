package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet119 {
    public static void main(String[] args) {
        System.out.println(getRow(1));
        System.out.println(getRow(2));
        System.out.println(getRow(3));
        System.out.println(getRow(4));
        System.out.println(getRow(5));

    }

    public static List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(1);
        for(int i=1;i<rowIndex+1;i++){
            list.add(0);
        }
        for (int i = 1; i < rowIndex+1; i++){
            for(int j=i; j > 0; j--) {
                Integer x=list.get(j)+list.get(j-1);
                list.set(j,x) ;
            }
        }
        return  list;
    }
}
