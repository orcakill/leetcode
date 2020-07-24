package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static leetcode.leet1025.Solution.divisorGame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        //leet1025
        System.out.println(divisorGame(1));















   //     int target =6;
   //     int  arr[]={3,2,4};
    //    int  arr1[]=twoSum(arr,target);
    //    System.out.println(Arrays.toString(arr1));
    }
//   两数之和
    public static  int[] twoSum(int[] nums, int target) {


        int  date[]=new int[2];
        for(int i=0;i<nums.length-1;i++) {
            System.out.println(i);
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] == target - nums[j]) {
                    date[0] = i;
                    date[1] = j;


                }
            }
        }
        return date;

    }


        public int[] twoSum1(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {
                    return new int[] { i, map.get(complement) };
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }



}


