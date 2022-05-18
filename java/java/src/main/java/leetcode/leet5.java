package leetcode;



public class leet5 {
    public static void main(String[] args) {
        String s1="babad";
        String s2="vckpzcfezppubykyxvwhbwvgezvannjnnxgaqvesrhdsgngcbbdpqeodzmqbkrwekakrukwxhqjeacxhkixruwshgwkjthmtqumvqcvhhoavarlwhpzbbniqrswvyhtfquioooejsbnxdnjrfhzpdrljcuenzjpzkyrgpxrbtchnzmdkekhmuqpoljvrpndzmogeuxjotdsyrrudligpgwcblaimqdqsgmjrbvyonugzsbkdhawmewiaccuvfnpftcjdjuljekiaipknorknwyx";
        String s3="bb";
        System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindrome(s2));
        System.out.println(longestPalindrome(s3));
    }

    public static String longestPalindrome(String s) {
        int len=s.length();
        if(len<2){
            return  s;
        }
        int max=1;
        int begin=0;
        char[] charArray=s.toCharArray();
       for(int i=0;i<len-1;i++){
           for(int j=i+1;j<len;j++){
               if(j-i+1>max&&compare(charArray,i,j)){
                      max=j-i+1;
                      begin=i;
               }
           }
       }
       return s.substring(begin,begin+max);
    }

    public  static  boolean  compare(char[] charArray,int left,int right){
        while (left<right){
            if(charArray[left]!=charArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return  true;
    }
}
