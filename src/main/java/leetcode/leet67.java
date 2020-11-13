package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class leet67 {
    public static void main(String[] args) {
        String a="11";
        String b="1";
        String c="1010";
        String d="1011";
        System.out.println(addBinary(a,b));
        System.out.println(addBinary(c,d));
    }

    public static String addBinary(String a, String b) {
        ArrayList<Integer> arrayList1=new ArrayList<>();
        ArrayList<Integer> arrayList2=new ArrayList<>();
        for(int i=a.length()-1;i>=0;i--){
            arrayList1.add(Integer.parseInt(a.substring(i,i+1)));
        }
        for(int i=b.length()-1;i>=0;i--){
            arrayList2.add(Integer.parseInt(b.substring(i,i+1)));
        }
        int length=0;
        if(a.length()>=b.length()){
            length=a.length();
        }
        else{
            length=b.length();
        }
        ArrayList<Integer> arrayList=new ArrayList<>();
        int m=0;
        for(int i=0;i<length;i++){
            int x=0;
            if(i>=arrayList2.size()){
                 x=arrayList1.get(i)+m;
            }
            else if (i>=arrayList1.size()){
                 x=arrayList2.get(i)+m;
            }
            else {
                 x = arrayList1.get(i) + arrayList2.get(i) + m;
            }
            if(x==2){
                x=0;
                arrayList.add(x);
                m=1;
            }
            else if(x==3){
                x=1;
                arrayList.add(x);
                m=1;
            }
            else{
                arrayList.add(x);
                m=0;
            }
        }
        if(m==1){
            arrayList.add(m);
        }
        String s1="";
        for(int i=0;i<arrayList.size();i++){
            s1=s1+arrayList.get(arrayList.size()-1-i);
        }
        return s1;
    }
}
