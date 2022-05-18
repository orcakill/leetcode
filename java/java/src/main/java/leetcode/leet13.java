package leetcode;

import java.util.ArrayList;
import java.util.List;



public class leet13 {
    public static void main(String[] args) {
        String s1 = "M";
        System.out.println(romanToInt(s1));

    }
    public static int romanToInt(String s) {
        int num=0;
        List<Integer> list1=new ArrayList<>(s.length());
        for(int i=0;i<s.length();i++){
            String s1=String.valueOf(s.charAt(i));
            int  i1=0;
            if(s1.equals("I")){
                i1=1;
            }
            if(s1.equals("V")){
                i1=5;
            }
            if(s1.equals("X")){
                i1=10;
            }
            if(s1.equals("L")){
                i1=50;
            }
            if(s1.equals("C")){
                i1=100;
            }
            if(s1.equals("D")){
                i1=500;
            }
            if(s1.equals("M")){
                i1=1000;
            }
            list1.add(i1);
        }
        if(list1.size()==1){
            return  list1.get(0);
        }
        for(int i=1;i<list1.size();i++){
            if(list1.get(i-1)<list1.get(i)){
                num=num-list1.get(i-1);
            }
            else{
                num=num+list1.get(i-1);
            }
            if(i==list1.size()-1){
                num=num+list1.get(i);
            }

        }


        return  num;
    }



}
