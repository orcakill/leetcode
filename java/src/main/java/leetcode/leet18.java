package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet18 {
    public static void main(String[] args) {
        int[] a ={1, 0, -1, 0, -2, 2};
        int target=0;
        System.out.println(fourSum(a,target));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        //定义一个二维list,用于处理并返回
        List<List<Integer>> quadruplets= new ArrayList<>();
        //判断nums是否为空或者长度是否小于4
        if(nums== null||nums.length<4){
            return  quadruplets;
        }
        //将数组的数字进行顺序排序处理
        Arrays.sort(nums);
        //定义length为数组的长度
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;


    }



}
