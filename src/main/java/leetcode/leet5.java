package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet5 {
    public static void main(String[] args) {
        String s1="babad";
        String s2="vckpzcfezppubykyxvwhbwvgezvannjnnxgaqvesrhdsgngcbbdpqeodzmqbkrwekakrukwxhqjeacxhkixruwshgwkjthmtqumvqcvhhoavarlwhpzbbniqrswvyhtfquioooejsbnxdnjrfhzpdrljcuenzjpzkyrgpxrbtchnzmdkekhmuqpoljvrpndzmogeuxjotdsyrrudligpgwcblaimqdqsgmjrbvyonugzsbkdhawmewiaccuvfnpftcjdjuljekiaipknorknwyx";
    //    System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindrome(s2));
    }

    public static String longestPalindrome(String s) {
        if(s.length()==0){
            return  null;
        }
       List<String> list=new ArrayList<>();
       for(int i=0;i<s.length();i++){
           list.add(s.substring(i,i+1));
           for(int j=i+1;j<s.length();j++){
               String  s1=s.substring(i,j+1);
               StringBuffer s2=new StringBuffer(s1);
               StringBuffer s3=s2.reverse();
               String       s4=new String(s3);
               if(s1.equals(s4)){
                   list.add(s1);
               }

           }
       }
        int num=0;
        for(int i=0;i<list.size()-1;i++){
            if(list.get(num).length()<list.get(i+1).length()){
                num=i+1;
            }
        }
        return list.get(num);
    }
}
