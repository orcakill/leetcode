package leetcode;

import leetclass.ListNode;

import java.util.ArrayList;

public class leet38 {
    public static void main(String[] args) {
        int  a=6;
        System.out.println(countAndSay(a));
    }

    public static String countAndSay(int n) {
        String countAndSay="1";
        if(n==1){
            return "1";
        }
       for(int i=0;i<n-1;i++){
           ArrayList<String> arrayList=new ArrayList<>();
          for(int j=0;j<countAndSay.length();j++){
              String ss1=countAndSay.substring(j,j+1);
              if(arrayList.size()==0){
                  arrayList.add(countAndSay.substring(j,j+1));
              }
              else {
                  String ss2=arrayList.get(arrayList.size()-1).substring(0,1);
                  String ss3=arrayList.get(arrayList.size()-1);
                  if(ss1.equals(ss2)){
                      arrayList.set(arrayList.size()-1, ss3+ss1);
                  }
                  else {
                      arrayList.add(countAndSay.substring(j,j+1));
                  }
              }


          }
           String  result="";
           for(int k=0;k<arrayList.size();k++){
               int length=arrayList.get(k).length();
               String val=arrayList.get(k).substring(0,1);
               result=result+length+val;

           }
           countAndSay=result;

       }


        return  countAndSay;
    }

}
