from typing import List


class Solution:
    def twoSum(self,nums: List[int], target: int) -> List[int]:
        for i in range(len(nums)-1):
            for j in range(i+1,len(nums)):
                if nums[i]+nums[j]==target:
                    return [i,j]





if __name__ == '__main__':
    nums=(3,2,4)
    target=6
    print(Solution.twoSum(0,nums, target))


